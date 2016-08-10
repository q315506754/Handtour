package com.handtours.service.com;

import com.handtours.common.utils.Copier;
import com.handtours.service.api.domain.core.req.PageReq;
import com.handtours.service.api.domain.core.res.PageRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/8/9 0009 10:42
 */
public interface R<REQ extends PageReq, RES extends PageRes<RS>, T, RS> {
    RES query(REQ req, Class<RES> cls, RequestBuilder<REQ> reqBuilder, QueryInvoker<T, REQ> invoker, CopierBuilder<RS> builder);

    interface RequestBuilder<REQ extends PageReq> {
        default PageRequest build(REQ req) {
            return new PageRequest(req.getPageIndex(), req.getPageSize());
        }
    }

    class DefaultRequestBuilder<REQ extends PageReq> implements RequestBuilder<REQ> {
        private REQ req;

        public DefaultRequestBuilder(REQ req) {
            this.req = req;
        }

        @Override
        public PageRequest build(REQ req) {
            return new PageRequest(this.req.getPageIndex(), this.req.getPageSize());
        }
    }

    class SortRequestBuilder<REQ extends PageReq> implements RequestBuilder<REQ> {
        private REQ req;
        private Sort sort;

        public SortRequestBuilder(REQ req, Sort sort) {
            this.req = req;
            this.sort = sort;
        }

        @Override
        public PageRequest build(REQ req) {
            return new PageRequest(this.req.getPageIndex(), this.req.getPageSize(), this.sort);
        }
    }

    interface QueryInvoker<T, REQ extends PageReq> {
        Page<T> invoke(REQ req, PageRequest page);
    }

    interface CopierBuilder<RS> {
        Copier<RS> build();
    }

}
