package transaction.service.weixin.impl;

import com.google.gson.Gson;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import transaction.service.weixin.WeixinPlatformService;
import transaction.service.weixin.dto.authorizationplatform.accesstoken.ComponentAccessTokenRequest;
import transaction.service.weixin.dto.authorizationplatform.accesstoken.ComponentAccessTokenResponse;
import transaction.service.weixin.dto.authorizationplatform.authorization.AuthorizationInfoRequest;
import transaction.service.weixin.dto.authorizationplatform.authorization.AuthorizationInfoResponse;
import transaction.service.weixin.dto.authorizationplatform.authorizer.AuthorizerInfoRequest;
import transaction.service.weixin.dto.authorizationplatform.authorizer.AuthorizerInfoResponse;
import transaction.service.weixin.dto.authorizationplatform.authorizeroption.AuthorizerOptionRequest;
import transaction.service.weixin.dto.authorizationplatform.authorizeroption.AuthorizerOptionResponse;
import transaction.service.weixin.dto.authorizationplatform.authorizeroption.UpdateAuthorizerOptionRequest;
import transaction.service.weixin.dto.authorizationplatform.authorizeroption.UpdateAuthorizerOptionResponse;
import transaction.service.weixin.dto.authorizationplatform.authorizertoken.UpdateAuthorizerRefreshTokenRequest;
import transaction.service.weixin.dto.authorizationplatform.authorizertoken.UpdateAuthorizerRefreshTokenResponse;
import transaction.service.weixin.dto.authorizationplatform.jsticket.JsTicketResponse;
import transaction.service.weixin.dto.authorizationplatform.precode.PreAuthCodeRequest;
import transaction.service.weixin.dto.authorizationplatform.precode.PreAuthCodeResponse;
import transaction.service.weixin.encryption.AesException;
import transaction.service.weixin.encryption.WXBizMsgCrypt;
import transaction.service.weixin.enums.InfoType;
import transaction.service.weixin.enums.OptionName;
import transaction.service.weixin.enums.Value;
import transaction.service.weixin.utils.OkhttpUtil;

/**
 * Created by zhengjianhui on 17/5/9.
 */
@Service
public class WeixinPlatformServiceImpl implements WeixinPlatformService {

    /**
     * 十分钟推送一次用于获取第三方平台的 token
     */
    private static AtomicReference<String> COMPONENT_VERIFY_TICKET = new AtomicReference<>();

    /**
     * 第三方平台compoment_access_token是第三方平台的下文中接口的调用凭据  有效期2h
     */
    private static AtomicReference<String> COMPONENT_ACCESS_TOKEN = new AtomicReference<>();

    /**
     * 预授权码，用于跳转微信授权的二维码扫描页面 有效期20min
     */
    private static AtomicReference<String> PRE_AUTH_CODE = new AtomicReference<>();

    /**
     * 微信公众号授权后，回调第三方平台的接口， 用于获取第三方平台调用微信公众号时需要的token
     */
    private static AtomicReference<String> AUTH_CODE = new AtomicReference<>();

    /**
     * 与关联小区进行缓存 授权方接口调用凭据 通过AUTH_CODE 获取 TOken 有效期2h
     */
    private static AtomicReference<String> AUTHORIZER_ACCESS_TOKEN = new AtomicReference<>();

    /**
     * 用于刷新 AUTHORIZER_ACCESS_TOKEN 接口调用凭据刷新令牌（在授权的公众号具备API权限时，才有此返回值），
     * 刷新令牌主要用于第三方平台获取和刷新已授权用户的access_token，只会在授权时刻提供，请妥善保存。
     * 一旦丢失，只能让用户重新授权，才能再次拿到新的刷新令牌 通过AUTH_CODE 获取 TOken 有效期2h
     */
    private static AtomicReference<String> AUTHORIZER_REFRESH_TOKEN = new AtomicReference<>();

    /**
     * 授权方appid
     */
    private static AtomicReference<String> AUTHORIZER_APPID = new AtomicReference<>();

    /**
     * jsticket
     */
    private static AtomicReference<String> JS_TICKET = new AtomicReference<>();

    /**
     * 授权方的帐号基本信息
     */
    private static AtomicReference<AuthorizerInfoResponse> AUTHORIZATIONINFO = new AtomicReference<>();

//    private static AtomicReference<AuthorizerInfoResponse> AUTHORIZER_OPTION = new AtomicReference<>();


    /**
     * 获取第三方平台token URL
     */
    private static final String COMPONENT_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";

    /**
     * 获取第三方平台预授权码 URL
     */
    private static final String PRE_AUTH_CODE_URL = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=";

    /**
     * 获取授权方信息 URL
     */
    private static final String AUTHORIZATION_INFO_URL = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=";

    /**
     * 刷新授权方token URL
     */
    private static final String AUTHORIZER_REFRESH_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token?component_access_token=";

    /**
     * jsticket 获取URl前半部分
     */
    private static final String JSAPI_TICKET_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=";

    /**
     * 第三方平台获取授权方的账号基本信息URL（AES）
     */
    private static final String AUTHORIZER_USER_INFO_URl = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info?component_access_token=";

    /**
     * 获取授权方选项信息(语音识别，地理位置上报等)
     */
    private static final String GET_AUTHORIZER_OPTION_URL = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_option?component_access_token=";

    /**
     * 设置授权方选项信息(语音识别，地理位置上报等)
     */
    private static final String UPDATE_AUTHORIZER_OPTION_URL = "https://api.weixin.qq.com/cgi-bin/component/api_set_authorizer_option?component_access_token=";

    /**
     * jsticket 获取URl后半部分
     */
    private static final String JSAPI_TICKET_URL_SUFFIX = "&type=jsapi";

    /**
     * 第三方平台appid
     */
    private static final String COMPONENT_APPID = "wx219c80e3b8cb1def";

    /**
     * 第三方平台消息加解密Key（AES）
     */
    private static final String COMPONENT_APPSECRETCOMPONENT = "f09e5f7e61819b90ca1d3cd67043a9c4";


    /**
     * 获取js sdk 的jsTicket
     * 通过微信指定的 Url + 第三方平台的 COMPONENT_ACCESS_TOKEN
     * 发送get 请求获取
     */
    @Override
    public void setJsapiTicket() {
        String response = OkhttpUtil.get(JSAPI_TICKET_URL_PREFIX + AUTHORIZER_ACCESS_TOKEN.get() + JSAPI_TICKET_URL_SUFFIX, Boolean.TRUE);
        Gson gson = new Gson();
        System.out.println(response);
        JsTicketResponse jsTicketResponse = gson.fromJson(response, JsTicketResponse.class);

        JS_TICKET.set(jsTicketResponse.getTicket());
        System.out.println("jsticket：" + jsTicketResponse.getTicket());

    }

    @Override
    public String getJsapiTicket() {
        if (JS_TICKET.get() == null) {
            setJsapiTicket();
        }

        return JS_TICKET.get();
    }

    /**
     * 授权事件回调URL
     * 授权事件回调URL 需要处理四件事
     * 1. 接收服务器每10分钟推送的 component_verify_ticket
     * 2. 授权方授权第三方平台后的回调
     * 3. 授权方更新第三方平台授权后的回调
     * 4. 授权方解除第三方平台授权后的回调
     *
     * 四种类型的事件都会发送 加密过的XML 需要通过第三方平台配置的
     * 1. 公众号消息校验Token: oumind-UJL-2015
     * 2. 公众号消息加解密Key:  GjD5MpWfrw36OtJd4Kgt0pFU5FbFrcqMLm2e5ni6lwz
     * 3. 第三方平台的 APPID
     *
     * 初始化解密类(解密类为官方demo中提供的)
     *
     * 通过随XML传递过来的三个参数
     * 1. msg_signature 签名
     * 2. timestamp     时间戳
     * 3. nonce         随机字符串
     *
     * 获取解密后的XML 进行后续操作
     *
     * @param msg_signature 签名
     * @param timestamp     时间戳
     * @param nonce         随机字符串
     * @param fromXML       未解密的XML
     */
    @Override
    public void authorizationEvent(String msg_signature, String timestamp, String nonce, String fromXML) {
        String component_verify_ticket = null;

        // 解密消息
        try {
            WXBizMsgCrypt pc = new WXBizMsgCrypt("oumind-UJL-2015", "GjD5MpWfrw36OtJd4Kgt0pFU5FbFrcqMLm2e5ni6lwz", "wx219c80e3b8cb1def");

            component_verify_ticket = pc.decryptMsg(msg_signature, timestamp, nonce, fromXML);
            Map<String, String> Encrypt = readStringXmlOut(component_verify_ticket);

            String infoType = Encrypt.get("InfoType");
            System.out.println(infoType);
            if (infoType.equals(InfoType.COMPONENT_VERIFY_TICKET)) {
                setCOMPONENT_VERIFY_TICKET(Encrypt.get("ComponentVerifyTicket"));
                System.err.println(InfoType.COMPONENT_VERIFY_TICKET);

            } else if (infoType.equals(InfoType.AUTHORIZED)) {
                System.err.println(InfoType.COMPONENT_VERIFY_TICKET);

            } else if (infoType.equals(InfoType.UPDATEAUTHORIZED)) {
                System.err.println(InfoType.COMPONENT_VERIFY_TICKET);

            } else if (infoType.equals(InfoType.UNAUTHORIZED)) {
                System.err.println(InfoType.COMPONENT_VERIFY_TICKET);

            }


        } catch (AesException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getCOMPONENT_VERIFY_TICKET() {
        return COMPONENT_VERIFY_TICKET.get();
    }

    @Override
    public void setCOMPONENT_VERIFY_TICKET(String ComponentVerifyTicket) {
        this.COMPONENT_VERIFY_TICKET.set(ComponentVerifyTicket);
        if (COMPONENT_ACCESS_TOKEN.get() == null) {
            setCOMPONENT_ACCESS_TOKEN();
        }
    }


    @Override
    public String getCOMPONENT_ACCESS_TOKEN() {
        return COMPONENT_ACCESS_TOKEN.get();
    }

    /**
     * 获取第三方平台的 Token
     * 1. 需要微信每10 分钟推送的 component_verify_ticket
     * 2. 第三方平台的APPID
     * 3. 第三方平台的 AppSecret:  f09e5f7e61819b90ca1d3cd67043a9c4
     *
     * 通过Post 请求 从微信给定的 URL 中获取
     */
    @Override
    public void setCOMPONENT_ACCESS_TOKEN() {
        if (COMPONENT_VERIFY_TICKET.get() != null) {
            ComponentAccessTokenRequest tokenRequest = new ComponentAccessTokenRequest();
            tokenRequest.setComponent_appid(COMPONENT_APPID);
            tokenRequest.setComponent_appsecret(COMPONENT_APPSECRETCOMPONENT);
            tokenRequest.setComponent_verify_ticket(getCOMPONENT_VERIFY_TICKET());

            Gson gson = new Gson();
            String response = OkhttpUtil.post(COMPONENT_ACCESS_TOKEN_URL, gson.toJson(tokenRequest), Boolean.TRUE);
            ComponentAccessTokenResponse componentAccessToken = gson.fromJson(response, ComponentAccessTokenResponse.class);
            System.err.println("COMPONENT_VERIFY_TICKET:" + componentAccessToken.getComponent_access_token());

            COMPONENT_ACCESS_TOKEN.set(componentAccessToken.getComponent_access_token());
        }

    }

    @Override
    public String getPRE_AUTH_CODE() {
        // 预授权码不为空且第三方的Token 不等于空则获取 预授权码
        if (PRE_AUTH_CODE.get() == null && COMPONENT_ACCESS_TOKEN.get() != null) {
            setPRE_AUTH_CODE();
        }


        return PRE_AUTH_CODE.get();
    }

    /**
     * 预授权码
     * 用于和 微信给定的URL 自定义回调接口跳转到网页授权页面
     *
     * 授权成功后回回调自定义回调接口
     */
    @Override
    public void setPRE_AUTH_CODE() {
        if (COMPONENT_VERIFY_TICKET.get() != null) {
            PreAuthCodeRequest preAuthCodeRequest = new PreAuthCodeRequest();
            preAuthCodeRequest.setComponent_appid(COMPONENT_APPID);

            Gson gson = new Gson();
            String response = OkhttpUtil.post(PRE_AUTH_CODE_URL + COMPONENT_ACCESS_TOKEN.get(), gson.toJson(preAuthCodeRequest), Boolean.TRUE);
            PreAuthCodeResponse preAuthCode = gson.fromJson(response, PreAuthCodeResponse.class);

            System.err.println("PreAuthCode:" + preAuthCode.getPre_auth_code());


            PRE_AUTH_CODE.set(preAuthCode.getPre_auth_code());
        }
    }

    @Override
    public String getAUTH_CODE() {
        return AUTH_CODE.get();
    }

    /**
     * 授权成功后回调接口
     *
     * @param AUTH_CODE 授权方的authorizer_access_token
     */
    @Override
    public void setAUTH_CODE(String AUTH_CODE) {
        this.AUTH_CODE.set(AUTH_CODE);
        setAUTHORIZER_ACCESS_TOKEN();
    }

    @Override
    public String getAUTHORIZER_ACCESS_TOKEN() {
        return AUTHORIZER_ACCESS_TOKEN.get();
    }

    @Override
    public void setAUTHORIZER_ACCESS_TOKEN() {
        AuthorizationInfoRequest authorizationInfoRequest = new AuthorizationInfoRequest();
        authorizationInfoRequest.setComponent_appid(COMPONENT_APPID);
        authorizationInfoRequest.setAuthorization_code(AUTH_CODE.get());

        Gson gson = new Gson();

        String response = OkhttpUtil.post(AUTHORIZATION_INFO_URL + COMPONENT_ACCESS_TOKEN.get(), gson.toJson(authorizationInfoRequest), true);
        AuthorizationInfoResponse authorizationInfoResponse = gson.fromJson(response, AuthorizationInfoResponse.class);

        System.err.println("获取公众号token 成功");
        System.err.println(response);
        System.err.println(authorizationInfoResponse);

        AUTHORIZER_ACCESS_TOKEN.set(authorizationInfoResponse.getAuthorization_info().getAuthorizer_access_token());
        AUTHORIZER_REFRESH_TOKEN.set(authorizationInfoResponse.getAuthorization_info().getAuthorizer_refresh_token());
        AUTHORIZER_APPID.set(authorizationInfoResponse.getAuthorization_info().getAuthorizer_appid());

    }

    @Override
    public String getAUTHORIZER_REFRESH_TOKEN() {
        return AUTHORIZER_REFRESH_TOKEN.get();
    }

    @Override
    public void updateAUTHORIZER_REFRESH_TOKEN() {
        if (AUTHORIZER_REFRESH_TOKEN.get() == null) {
            return;
        }
        UpdateAuthorizerRefreshTokenRequest refreshTokenRequest = new UpdateAuthorizerRefreshTokenRequest();
        refreshTokenRequest.setComponent_appid(COMPONENT_APPID);
        refreshTokenRequest.setAuthorizer_appid(AUTHORIZER_APPID.get());
        refreshTokenRequest.setAuthorizer_refresh_token(AUTHORIZER_REFRESH_TOKEN.get());

        Gson gson = new Gson();

        String response = OkhttpUtil.post(AUTHORIZER_REFRESH_TOKEN_URL + COMPONENT_ACCESS_TOKEN.get(), gson.toJson(refreshTokenRequest), true);
        UpdateAuthorizerRefreshTokenResponse refreshTokenResponse = gson.fromJson(response, UpdateAuthorizerRefreshTokenResponse.class);
        System.err.println("更新授权方token 成功");
        System.err.println(refreshTokenResponse);

        AUTHORIZER_REFRESH_TOKEN.set(refreshTokenResponse.getAuthorizer_refresh_token());
        AUTHORIZER_ACCESS_TOKEN.set(refreshTokenResponse.getAuthorizer_access_token());

    }


    /**
     * 向微信服务器获取 授权方的帐号基本信息
     */
    @Override
    public void authorizerInfo() {
        if (COMPONENT_ACCESS_TOKEN.get() == null) {
            return;
        }

        AuthorizerInfoRequest request = new AuthorizerInfoRequest();
        request.setComponent_appid(COMPONENT_APPID);
        request.setAuthorizer_appid(AUTHORIZER_APPID.get());

        Gson gson = new Gson();
        String response = OkhttpUtil.post(AUTHORIZER_USER_INFO_URl + COMPONENT_ACCESS_TOKEN.get(), gson.toJson(request), Boolean.TRUE);

        AuthorizerInfoResponse authorizationInfoResponse = gson.fromJson(response, AuthorizerInfoResponse.class);

        AUTHORIZATIONINFO.set(authorizationInfoResponse);
    }


    @Override
    public AuthorizerInfoResponse getAuthorizerUserInfo() {
        if (AUTHORIZATIONINFO.get() == null && COMPONENT_ACCESS_TOKEN.get() != null) {
            authorizerInfo();
        }
        return AUTHORIZATIONINFO.get();
    }

    /**
     * 获取功能开关状态
     * @param name      功能名
     * @return          功能状态
     */
    @Override
    public AuthorizerOptionResponse getAuthorizerOption(OptionName name) {
        if (AUTHORIZER_APPID.get() == null) {
            return null;
        }
        AuthorizerOptionRequest request = new AuthorizerOptionRequest();
        request.setComponent_appid(COMPONENT_APPID);
        request.setAuthorizer_appid(AUTHORIZER_APPID.get());
        request.setOption_name(name.getDescription());

        Gson gson = new Gson();
        String response = OkhttpUtil.post(GET_AUTHORIZER_OPTION_URL + COMPONENT_ACCESS_TOKEN.get(), gson.toJson(request), Boolean.TRUE);

        return gson.fromJson(response, AuthorizerOptionResponse.class);

    }

    /**
     * 更新功能开关
     * @param name    功能名
     * @param value   开关值
     * @return        更新结果
     */
    @Override
    public UpdateAuthorizerOptionResponse updateAuthorizerOption(OptionName name, Value value) {
        UpdateAuthorizerOptionRequest request = new UpdateAuthorizerOptionRequest();
        request.setComponent_appid(COMPONENT_APPID);
        request.setAuthorizer_appid(AUTHORIZER_APPID.get());
        request.setOption_name(name.getDescription());
        request.setOption_value(value.getCode());

        Gson gson = new Gson();
        String response = OkhttpUtil.post(UPDATE_AUTHORIZER_OPTION_URL + COMPONENT_ACCESS_TOKEN, gson.toJson(request), Boolean.TRUE);

        UpdateAuthorizerOptionResponse updateAuthorizerOptionResponse = gson.fromJson(response, UpdateAuthorizerOptionResponse.class);

        return updateAuthorizerOptionResponse;

    }

    @Override
    public String getAuthorizerAppid() {
        return AUTHORIZER_APPID.get();
    }

    /**
     * @return Map
     * @description 将xml字符串转换成map
     */
    public static Map<String, String> readStringXmlOut(String xmlStr) {
        Map<String, String> map = new HashMap<String, String>();
        // 1：创建SAX对象
        SAXReader read = new SAXReader();
        try {
            Document doc = DocumentHelper.parseText(xmlStr);
            Element rootElt = doc.getRootElement(); // 获取根节点
            List<Element> list = rootElt.elements();//获取根节点下所有节点
            for (Element element : list) {  //遍历节点
                map.put(element.getName(), element.getText()); //节点的name为map的key，text为map的value
//                System.out.println(element.getName());
//                System.out.println(element.getText());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
