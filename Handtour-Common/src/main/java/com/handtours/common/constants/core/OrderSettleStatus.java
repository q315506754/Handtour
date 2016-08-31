package com.handtours.common.constants.core;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/8/15 0015 15:11
 */
public enum OrderSettleStatus implements StatusEnum{
    NOT_YET(0,"未结算"),
    ALREADY(1,"已结算");

    private final int status;
    private final String name;

    public int getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    OrderSettleStatus(int status, String name) {
        this.status = status;
        this.name = name;
    }
}
