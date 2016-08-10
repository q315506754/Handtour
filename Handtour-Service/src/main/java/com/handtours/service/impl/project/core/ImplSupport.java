package com.handtours.service.impl.project.core;

import com.handtours.common.utils.Copier;
import com.handtours.service.api.domain.core.req.DeleteReq;
import com.handtours.service.api.domain.core.req.PageReq;
import com.handtours.service.api.domain.core.req.SaveReq;
import com.handtours.service.api.domain.core.req.UpdateReq;
import com.handtours.service.api.domain.core.res.*;
import com.handtours.service.com.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 15:50
 */
public abstract class ImplSupport<T, ID extends Serializable,
        SREQ extends SaveReq, SRES extends SaveRes<ID>,
        RREQ extends PageReq, RRES extends PageRes<RSONE>, RSONE,
        UREQ extends UpdateReq<ID>, URES extends UpdateRes,
        DREQ extends DeleteReq<ID>, DRES extends DeleteRes
        > implements ApplicationContextAware, C<T, SREQ, SRES>, R<RREQ, RRES, T, RSONE>, U<T, ID, UREQ, URES>, D<T, ID, DREQ, DRES> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected ApplicationContext applicationContext;

    //    protected ResourceBundle resourceBundle = new MessageSourceResourceBundle();
    private static final ClassPathResource excpr = new ClassPathResource("msg/excetions.properties");
    private static final Properties prop = new Properties();

    static {
        try {
            prop.load(new InputStreamReader(excpr.getInputStream(), "utf8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getExMsg(int enu, Object... args) {
        String format = MessageFormat.format(prop.getProperty(String.valueOf(enu)), args);
        return format;
    }

    public static <RES extends Res> RES retEx(Class<RES> cls, int enu, Object... args) {
        RES ret = null;
        try {
            ret = cls.newInstance();

            setEx(ret, enu, args);
        } catch (Exception e) {
            e.printStackTrace();

            ret = (RES) new Res();
            ret.set(-100, e.getMessage());
        }

        return ret;
    }

    public static <RES extends Res> void setEx(RES obj, int enu, Object... args) {
        obj.set(enu, getExMsg(enu, args));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @Override
    public SRES save(SREQ req, Class<SRES> cls, ReqCopierChain<T> reqProcessor) {
        SRES ret = null;
        try {
            ret = cls.newInstance();

            ID unitqueId = get_C_requestId(req);

            if (unitqueId != null) {
                T one = getDao().findOne(unitqueId);
                if (one != null) {
                    setEx(ret, Ex.record_already_exist, generateRecordTitle());
                }
            }

            if (ret.getCode() == 0) {
                Copier<T> to = Copier.to(getEntityClass());

                if (reqProcessor != null) {
                    reqProcessor.chainPostProcessor(to);
                }

                T stage1 = to.from(req);
                logger.debug("stage1:" + stage1);
                T saved = getDao().save(stage1);

                //TODO
//                ret.setGeneratedId();
            }
        } catch (Exception e) {
            e.printStackTrace();

            ret = (SRES) new SaveRes();
            ret.set(-100, e.getMessage());
        }

        return ret;
    }

    @Override
    public RRES query(RREQ req, Class<RRES> cls, RequestBuilder<RREQ> reqBuilder, QueryInvoker<T, RREQ> invoker, CopierBuilder<RSONE> builder) {
        RRES ret = null;
        try {
            ret = Copier.to(cls).from(req);

            PageRequest pageRequest = reqBuilder.build(req);

            Page<T> invoke = invoker.invoke(req, pageRequest);

            Copier<RSONE> copier = builder.build();

            Page<RSONE> mapped = invoke.map(t -> copier.from(t));

            ret.setDataList(mapped.getContent());
            ret.setTotalPage(mapped.getTotalPages());
            ret.setTotalRecords((int) mapped.getTotalElements());
        } catch (Exception e) {
            e.printStackTrace();

            ret = (RRES) new PageRes();
            ret.set(-101, e.getMessage());
        }

        return ret;
    }

    @Override
    public URES update(UREQ req, Class<URES> cls, ReqCopierChain<T> copierChain) {
        URES ret = null;
        ID unitqueId = req.getId();
        try {
            ret = cls.newInstance();

            T one = null;
            if (unitqueId != null) {
                one = getDao().findOne(unitqueId);
            } else {
                logger.warn("no id for update:" + req);
            }

            if (one == null) {
                setEx(ret, Ex.record_note_exist, generateRecordTitle());
            }

            if (ret.getCode() == 0) {
                Copier<T> to = Copier.to(one);

                if (copierChain != null) {
                    copierChain.chainPostProcessor(to);
                }

                logger.debug("obj in db:" + one);

                //do not copy null
                T stage1 = to.from(req, false);

                logger.debug("expected updated obj:" + stage1);
                T saved = getDao().save(stage1);

            }
        } catch (Exception e) {
            e.printStackTrace();

            ret = (URES) new UpdateRes();
            ret.set(-102, e.getMessage());
        }

        return ret;
    }

    @Override
    public DRES delete(DREQ req, Class<DRES> cls, ReqCopierChain<T> copierChain) {
        DRES ret = null;

        ID id = req.getId();
        try {
            ret = cls.newInstance();
        }catch (Exception e) {
            e.printStackTrace();

            ret = (DRES) new DeleteRes();
            ret.set(-103, e.getMessage());
        }

        //logic delete
        if (req.getLogicalDeletion()!= null && req.getLogicalDeletion()) {
            UpdateReq<ID> updateReq = new UpdateReq<ID>(id);
            updateReq.setDeleted(true);
            UpdateRes update = this.update((UREQ)updateReq,(Class)UpdateRes.class,null );
            if (ret != null) {
                ret.set(update.getCode(),update.getMsg());
            }
        } else {
            //physical delete
        }

        return ret;
    }

    public abstract JpaRepository<T, ID> getDao();

    public abstract Class<T> getEntityClass();

    protected Object[] generateRecordTitle() {
        return null;
    }

    protected ID get_C_requestId(SREQ req) {
        return null;
    }

    protected void get_C_copy_chain(Copier<T> to) {

    }


}
