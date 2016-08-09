package com.handtours.service.dao.back;

import com.handtours.service.model.back.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/7/21 0021 14:35
 */
@Transactional
public interface UserDao extends CrudRepository<User, String> {

}
