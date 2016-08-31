package com.handtours.service.dao.common;

import com.handtours.service.model.core.GroupPerson;
import com.handtours.service.model.core.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/7/21 0021 14:35
 */
@Transactional
public interface GroupPersonDao extends JpaRepository<GroupPerson, Long> {
}
