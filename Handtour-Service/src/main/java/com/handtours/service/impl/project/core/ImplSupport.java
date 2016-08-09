package com.handtours.service.impl.project.core;

import com.handtours.common.utils.Copier;
import com.handtours.service.api.domain.core.req.SaveReq;
import com.handtours.service.api.domain.core.res.SaveRes;
import com.handtours.service.com.C;
import com.handtours.service.com.Ex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.CrudRepository;

import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 15:50
 */
public abstract class ImplSupport<T, ID extends Serializable,SREQ extends SaveReq,SRES extends SaveRes<ID>> implements ApplicationContextAware,C<SREQ,SRES> {
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

    public static String  getExMsg(Ex enu,Object... args) {
        String format = MessageFormat.format(prop.getProperty(String.valueOf(enu.getCode())), args);
        return format;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @Override
    public SRES save(SREQ req, Class<SRES> cls) {
        SRES ret = null;
        try {
            ret = cls.newInstance();

            ID unitqueId = get_C_requestId(req);

            if (unitqueId != null) {
                T one = getDao().findOne(unitqueId);
                if (one!=null) {
                    ret.set(Ex.record_already_exist.getCode(),getExMsg(Ex.record_already_exist,get_C_ex_exist()));
                }
            }

            if (ret.getCode() == 0) {
                Copier<T> to = Copier.to(getEntityClass());

                get_C_copy_chain(to);

                T stage1 = to.from(req);
                logger.debug("stage1:" + stage1);
                T saved = getDao().save(stage1);

                //TODO
//                ret.setGeneratedId();
            }


        } catch (Exception e) {
            e.printStackTrace();

            ret = (SRES)new SaveRes();
            ret.set(-100,e.getMessage());
        }

        return ret;
    }

    public abstract CrudRepository<T, ID> getDao() ;
    public abstract Class<T> getEntityClass() ;


    protected  ID get_C_requestId(SREQ req){
        return null;
    }
    protected  Object[] get_C_ex_exist(){
        return null;
    }
    protected  void get_C_copy_chain(Copier<T> to){

    }



}
