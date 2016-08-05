package com.handtours.service.api.domain.back.res;

import com.handtours.service.api.domain.core.res.Res;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:49
 */
public class LoginRes extends Res {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
