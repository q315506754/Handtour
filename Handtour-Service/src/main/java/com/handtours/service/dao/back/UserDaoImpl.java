package com.handtours.service.dao.back;

import com.handtours.service.model.back.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/7/21 0021 16:44
 */
public  class UserDaoImpl implements  UserDaoCustom{
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    public UserInfo findByMobileCustom(String mobile) {
        return null;
    }


    @Override
    public UserInfo saveCustom(UserInfo accountInfo) {
        logger.debug("save Custom...");
        System.out.println("save Custom");
        logger.debug(""+em);
        em.persist(accountInfo);
        return accountInfo;
    }
}
