package com.handtours.service.api.project.back;
import com.handtours.service.api.domain.back.req.*;
import com.handtours.service.api.domain.back.res.*;

import java.util.List;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:43
 */
public interface IUser {
    LoginRes login(LoginReq param);

    SaveUserRes save(SaveUserReq params);

    QueryUserRes query(QueryUserReq params);

    UpdateUserRes update(UpdateUserReq params);

    DeleteUserRes delete(DeleteUserReq params);


}
