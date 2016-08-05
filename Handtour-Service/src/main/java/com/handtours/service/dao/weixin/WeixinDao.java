package com.handtours.service.dao.weixin;

import com.handtours.service.model.weixin.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/8/5 0005 14:53
 */
public interface WeixinDao extends CrudRepository<UserInfo, String> {

}
