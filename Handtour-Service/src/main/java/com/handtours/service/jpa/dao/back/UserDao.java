package com.handtours.service.jpa.dao.back;

import com.handtours.service.jpa.model.back.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/7/21 0021 14:35
 */
public interface UserDao extends Repository<UserInfo, String> {

     UserInfo save(UserInfo accountInfo);

    // 你需要做的，仅仅是新增如下一行方法声明
     UserInfo findByMobile(String mobile);

    @Query("select a from UserInfo a where a.mobile like  CONCAT('%',CONCAT(:mobile, '%'))")
     Page<UserInfo> findByMobileRegex(
            @Param("mobile") String mobile, Pageable pageable);
}
