package com.handtours.service.dao.back;

import com.handtours.service.model.back.User;

public interface UserMapper {
    int deleteByPrimaryKey(String mobile);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String mobile);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}