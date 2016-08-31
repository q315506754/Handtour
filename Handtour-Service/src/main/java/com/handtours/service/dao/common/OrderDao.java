package com.handtours.service.dao.common;

import com.handtours.service.model.core.CardUser;
import com.handtours.service.model.core.Order;
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
public interface OrderDao extends JpaRepository<Order, Long>,OrderDaoCustom,JpaSpecificationExecutor<Order> {
    @Override
    Order save(Order s);
}
