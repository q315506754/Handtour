package com.handtours.service.dao.back;

import com.handtours.service.model.back.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/7/21 0021 16:44
 */
public  class UserDaoImpl implements  UserDao{
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Override
//    public UserInfo findRandomOne() {
//        UserInfo ret = new UserInfo();
//        logger.debug("findRandomOne...");
//        return ret;
//    }


    @PersistenceContext
    private EntityManager em;


    @Override
    public UserInfo findByMobile(String mobile) {
        return null;
    }

    @Override
    public Page<UserInfo> findByMobileRegex(@Param("mobile") String mobile, Pageable pageable) {
        return null;
    }

    @Override
    public UserInfo save(UserInfo accountInfo) {
        logger.debug("save...");
        System.out.println("save");
        logger.debug(""+em);
        em.persist(accountInfo);
        return accountInfo;
    }
}
