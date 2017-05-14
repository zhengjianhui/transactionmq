package transaction.controller.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import transaction.service.weixin.WeixinPlatformService;
import transaction.service.weixin.WeixinUserAuthorizationService;
import transaction.service.weixin.dto.user.UserInfoResponse;

/**
 * Created by zhengjianhui on 17/5/12.
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 36000)
public class WeixinUserAuthorizationcController {

    @Autowired
    private WeixinPlatformService weixinPlatformService;

    @Autowired
    private WeixinUserAuthorizationService weixinUserAuthorizationService;


//    @RequestMapping(value = "/userAuthorize", method = RequestMethod.GET)
//    public Map<String, String> getUserAuthorizeUrl(@RequestParam(value = "", required = true)String redirect_uri,
//                                                   @RequestParam(value = "", required = true)String scope,
//                                                   @RequestParam(value = "", required = false)String state,
//                                                   @RequestParam(value = "", required = false)String wechat_redirect) {
//
//        AuthorizerInfoResponse s = weixinPlatformService.getAuthorizerUserInfo();
//
//        String url
//                = USER_AUTHORIZE_URL.replaceAll("APPID", weixinPlatformService.getAuthorizerAppid())
//                .replace("REDIRECT_URI", redirect_uri)
//                .replace("SCOPE", scope);
//
//        if(StringUtils.isNotBlank(state)) {
//            url = url.replace("STATE", state);
//        }
//
//        // wechat_redirect 待实现
//
//        System.out.println(url);
//
//        Map<String, String> result = new HashMap<>(1);
//        result.put("url", url);
//
//        return result;
//    }


    @RequestMapping(value = "/users/appid", method = RequestMethod.GET)
    public Map<String, String> getAppid() {
        Map<String, String> result = new HashMap<>(1);
        result.put("appid", weixinPlatformService.getAuthorizerAppid());

        return result;
    }


    @RequestMapping(value = "/users/accessToken", method = RequestMethod.GET)
    public Map<String, String> getaccessToken() {
        Map<String, String> result = new HashMap<>(1);
        result.put("accessToken", weixinPlatformService.getAUTHORIZER_ACCESS_TOKEN());

        return result;
    }


    @RequestMapping(value = "/users/saveCode", method = RequestMethod.POST)
    public UserInfoResponse saveCode(@RequestParam(value = "code", required = true) String code) {
        return weixinUserAuthorizationService.saveCode(code);
    }

    @RequestMapping(value = "/users/userToken", method = RequestMethod.GET)
    public void userToken(@RequestParam(value = "code", required = true) String code) {
        weixinUserAuthorizationService.getUserInfo();
    }

    @RequestMapping(value = "/users/code", method = RequestMethod.POST)
    public void code(@RequestParam(value = "code", required = true) String code,
                                     @RequestParam(value = "state", required = true) String state,
                                     @RequestParam(value = "appid", required = true) String appid) {

        System.out.println(code);
        System.out.println(state);
        System.out.println(appid);
        weixinUserAuthorizationService.saveCode(code);

    }





}
