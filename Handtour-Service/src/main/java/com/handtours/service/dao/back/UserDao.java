package com.handtours.service.dao.back;

import com.handtours.service.model.back.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/7/21 0021 14:35
 */
@Transactional
public interface UserDao extends CrudRepository<UserInfo, String>,UserDaoCustom {

     UserInfo save(UserInfo accountInfo);

    @Query("select u from #{#entityName} u where mobile=:mb")
     UserInfo findByMobile(@Param("mb") String mobile);

    @Query("select u from #{#entityName} u where mobile=?1")
    ProjectionForAddress findUserInfoByMobile(String mobile);

    @Query("select a from back.UserInfo a where a.mobile like  CONCAT('%',CONCAT(:mobile, '%'))")
     Page<UserInfo> findByMobileRegex(
            @Param("mobile") String mobile, Pageable pageable);

    @Modifying
    @Query("update #{#entityName} u set u.name =?2 where u.mobile=?1")
    int updateName(String mobile,String name);

}
