package com.handtours.service.dao.common;

import com.handtours.service.model.core.Order;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/8/23 0023 16:40
 */
public interface OrderDaoCustom {
    Order save(Order s);

    void refresh(Order s);

}
