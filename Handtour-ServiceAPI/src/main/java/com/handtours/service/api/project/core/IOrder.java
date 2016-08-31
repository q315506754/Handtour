package com.handtours.service.api.project.core;

import com.handtours.service.api.domain.core.req.order.DeleteOrderReq;
import com.handtours.service.api.domain.core.req.order.QueryOrderReq;
import com.handtours.service.api.domain.core.req.order.SaveOrderReq;
import com.handtours.service.api.domain.core.req.order.UpdateOrderReq;
import com.handtours.service.api.domain.core.res.order.DeleteOrderRes;
import com.handtours.service.api.domain.core.res.order.QueryOrderRes;
import com.handtours.service.api.domain.core.res.order.SaveOrderRes;
import com.handtours.service.api.domain.core.res.order.UpdateOrderRes;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:43
 */
public interface IOrder {
    SaveOrderRes save(SaveOrderReq params);

    QueryOrderRes query(QueryOrderReq params);

    UpdateOrderRes update(UpdateOrderReq params);

    DeleteOrderRes delete(DeleteOrderReq params);

}
