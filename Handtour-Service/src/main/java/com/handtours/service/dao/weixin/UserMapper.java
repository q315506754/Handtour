package com.handtours.service.dao.weixin;

import com.handtours.service.model.weixin.User;

public interface UserMapper {
    int deleteByPrimaryKey(String mobile);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String mobile);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}