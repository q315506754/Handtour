package com.handtours.service.dao.back;

import com.handtours.service.model.back.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/7/21 0021 14:35
 */
@Transactional
public interface UserDao extends JpaRepository<User, String> {
    @Query("select u from bg.UserInfo u where mobile like ?1")
    Page<User> queryList(String keyword, Pageable pageable);
}
