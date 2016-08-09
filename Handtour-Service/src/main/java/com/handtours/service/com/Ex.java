package com.handtours.service.com;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/8/9 0009 10:20
 */
public enum  Ex {
    record_already_exist(10000);

    private final int code;
    Ex(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
