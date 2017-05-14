package transaction.service.weixin.impl;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

import transaction.service.weixin.WeixinPlatformService;
import transaction.service.weixin.WeixinUserAuthorizationService;
import transaction.service.weixin.dto.user.UserInfoResponse;
import transaction.service.weixin.dto.user.UserTokenResponse;
import transaction.service.weixin.utils.OkhttpUtil;

/**
 * Created by zhengjianhui on 17/5/12.
 */
@Service
public class WeixinUserAuthorizationServiceImpl implements WeixinUserAuthorizationService {

    @Autowired
    private WeixinPlatformService weixinPlatformService;

    private static final String USER_AUTHORIZE_URL = "https://api.weixin.qq.com/sns/oauth2/component/access_token?";

    private static final String USER_INFO_URL = " https://api.weixin.qq.com/sns/userinfo?access_token=";

    private static AtomicReference<String> USER_CODE = new AtomicReference<>();

    private static AtomicReference<UserTokenResponse> USER_TOKEN = new AtomicReference<>();

    @Override
    public UserInfoResponse saveCode(String code) {
        USER_CODE.set(code);

        System.err.println(code);
        if(USER_TOKEN.get() == null) {
            setUserToken();
        }

        return getUserInfo();
    }

    @Override
    public void setUserToken() {
        if (weixinPlatformService.getCOMPONENT_ACCESS_TOKEN() == null) {
            return;
        }


        String appid = "appid=" + weixinPlatformService.getAuthorizerAppid();
        String code = "&code=" + USER_CODE.get();
        String grant_type = "&grant_type=authorization_code&";
        String component_appid = "component_appid=wx219c80e3b8cb1def&";
        String component_access_token = "component_access_token=" + weixinPlatformService.getCOMPONENT_ACCESS_TOKEN();

        String url = USER_AUTHORIZE_URL + appid + code + grant_type + component_appid + component_access_token;
        String response = OkhttpUtil.get(url, Boolean.TRUE);

        Gson gson = new Gson();

        UserTokenResponse userTokenResponse = gson.fromJson(response, UserTokenResponse.class);
        USER_TOKEN.set(userTokenResponse);

        System.out.println(response);


    }


    @Override
    public UserInfoResponse getUserInfo() {
        UserTokenResponse token = USER_TOKEN.get();
        String url = USER_INFO_URL + token.getAccess_token() + "&openid=" + token.getOpenid() + "&lang=zh_CN";

        String response = OkhttpUtil.get(url, Boolean.TRUE);
        Gson gson = new Gson();

        System.out.println(response);

        UserInfoResponse userInfoResponse = gson.fromJson(response, UserInfoResponse.class);



        return userInfoResponse;
    }


    public static void main(String[] args) {
        String url = USER_INFO_URL + "7S2SRTX_a2u9V9K-psCNGXA-PvguNTzfniv6gNfLT2p_ehSw6_izcKiXD-ZCcjH05zZeG9X008HhmNlojKkoE78XMThxFKr0jPkPJQS_UglOVA-hDUJOLem0GKZSEbnk&openid=ojTEdv62ytwxQCFZNHGpIeDDurIc&lang=zh_CN";

        String response = OkhttpUtil.get(url, Boolean.TRUE);
        System.out.println(response);
    }
}
