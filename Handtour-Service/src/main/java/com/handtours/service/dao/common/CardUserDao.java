package com.handtours.service.dao.common;

import com.handtours.service.model.back.User;
import com.handtours.service.model.core.CardUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/7/21 0021 14:35
 */
@Transactional
public interface CardUserDao extends JpaRepository<CardUser, String> {
    @Query("select u from #{#entityName} u where isDeleted is null or isDeleted = 0 and mobile like  CONCAT('%',CONCAT(?1, '%'))")
    Page<CardUser> queryList(String keyword, Pageable pageable);

    @Override
    @Query("select u from #{#entityName} u where isDeleted is null or isDeleted = 0 ")
    Page<CardUser> findAll(Pageable pageable);

    @Override
    @Query("select u from #{#entityName} u where mobile=?1 and (isDeleted is null or isDeleted = 0)")
    CardUser findOne(String id);
}
