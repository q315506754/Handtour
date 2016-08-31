package com.handtours.common.constants.core;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/8/15 0015 15:11
 */
public enum OrderStatus implements StatusEnum{
    NOT_HANDLED(0,"未处理"),
    AL_HANDLED(1,"已处理"),
    AL_CANCELLED(2,"已取消"),
    AL_WENT(3,"已出行");

    private final int status;
    private final String name;

    public int getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    OrderStatus(int status, String name) {
        this.status = status;
        this.name = name;
    }
}
