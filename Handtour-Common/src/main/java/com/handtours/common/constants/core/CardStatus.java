package com.handtours.common.constants.core;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/8/15 0015 15:11
 */
public enum CardStatus implements StatusEnum{
    NOT_AUDITED(0,"未审核"),
    AUDITED(1,"已审核");

    private final int status;
    private final String name;

    public int getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    CardStatus(int status, String name) {
        this.status = status;
        this.name = name;
    }
}
