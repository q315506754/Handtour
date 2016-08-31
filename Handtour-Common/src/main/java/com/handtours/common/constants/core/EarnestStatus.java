package com.handtours.common.constants.core;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/8/15 0015 15:11
 */
public enum EarnestStatus implements StatusEnum{
    NOT_YET(0,"未付定金"),
    ALREADY(1,"已付定金");

    private final int status;
    private final String name;

    public int getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    EarnestStatus(int status, String name) {
        this.status = status;
        this.name = name;
    }
}
