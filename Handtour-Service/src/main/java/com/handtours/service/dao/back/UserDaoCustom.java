package com.handtours.service.dao.back;

import com.handtours.service.model.back.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/7/21 0021 14:35
 */
@Transactional
public interface UserDaoCustom  {

    UserInfo saveCustom(UserInfo accountInfo);


    // 你需要做的，仅仅是新增如下一行方法声明
    UserInfo findByMobileCustom(String mobile);


}
