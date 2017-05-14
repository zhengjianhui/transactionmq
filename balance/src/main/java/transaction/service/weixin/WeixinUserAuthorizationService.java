package transaction.service.weixin;

import transaction.service.weixin.dto.user.UserInfoResponse;

/**
 * Created by zhengjianhui on 17/5/12.
 */
public interface WeixinUserAuthorizationService {

    UserInfoResponse saveCode(String code);

    void setUserToken();

    UserInfoResponse getUserInfo();
}
