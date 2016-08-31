package com.handtours.common.constants.core;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/8/15 0015 15:11
 */
public enum OrderDiscountStatus implements StatusEnum{
    NOT_YET(0,"未返佣"),
    ALREADY(1,"已返佣");

    private final int status;
    private final String name;

    public int getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    OrderDiscountStatus(int status, String name) {
        this.status = status;
        this.name = name;
    }
}
