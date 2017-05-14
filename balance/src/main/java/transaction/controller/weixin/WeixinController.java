package transaction.controller.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import transaction.service.weixin.WeixinPlatformService;
import transaction.service.weixin.dto.authorizationplatform.authorizer.AuthorizerInfoResponse;
import transaction.service.weixin.dto.authorizationplatform.authorizeroption.AuthorizerOptionResponse;
import transaction.service.weixin.dto.authorizationplatform.authorizeroption.UpdateAuthorizerOptionResponse;
import transaction.service.weixin.enums.OptionName;
import transaction.service.weixin.enums.OptionValue;

/**
 * Created by zhengjianhui on 17/5/8.
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 36000)
public class WeixinController {

    @Autowired
    private WeixinPlatformService weixinPlatformService;


    @RequestMapping(value = "/event/auth")
    public void aaa(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            InputStream input = request.getInputStream();
            String fromXML = StreamUtils.copyToString(input, Charset.forName("utf-8"));
            System.err.println(fromXML);

            // 微信回调附带的参数  用于解密时使用
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String encrypt_type = request.getParameter("encrypt_type");
            String msg_signature = request.getParameter("msg_signature");

            weixinPlatformService.authorizationEvent(msg_signature, timestamp, nonce, fromXML);

            out = response.getWriter();
            out.write("success");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }

    }


    @RequestMapping(value = "/event/auth/code", method = RequestMethod.GET)
    public Map<String, String> getPreAuthCode() {
        Map<String, String> result = new HashMap<>(1);
        result.put("pre_auth_code", weixinPlatformService.getPRE_AUTH_CODE());

        return result;
    }



    @RequestMapping(value = "/event/auth/callback", method = RequestMethod.GET)
    public void callback(@RequestParam(value = "auth_code", required = true) String auth_code,
                         @RequestParam(value = "expires_in", required = true) String expires_in) {

        System.out.println(auth_code);
        System.out.println(expires_in);

        weixinPlatformService.setAUTH_CODE(auth_code);

    }

    @RequestMapping(value = "/event/token/refresh", method = RequestMethod.PUT)
    public void updateRefresh() {
        weixinPlatformService.updateAUTHORIZER_REFRESH_TOKEN();
    }



    @RequestMapping(value = "/event/jsticket", method = RequestMethod.GET)
    public Map<String, String> jsticket() {
        Map<String, String> result = new HashMap<>(1);
        String jsticket = weixinPlatformService.getJsapiTicket();
        result.put("jsticket", jsticket);

        return result;
    }

    @RequestMapping(value = "/authorizationInfo", method = RequestMethod.GET)
    public AuthorizerInfoResponse Authorization() {
        return weixinPlatformService.getAuthorizerUserInfo();

    }


    @RequestMapping(value = "/authorization/option", method = RequestMethod.GET)
    public AuthorizerOptionResponse option(@RequestParam(value = "name", required = true) OptionName name) {
        return weixinPlatformService.getAuthorizerOption(name);

    }

    @RequestMapping(value = "/authorization/option", method = RequestMethod.PUT)
    public UpdateAuthorizerOptionResponse updateOption(@RequestParam(value = "name", required = true) OptionName name,
                                                       @RequestParam(value = "value", required = true)OptionValue value) {
        return weixinPlatformService.updateAuthorizerOption(name, value);

    }
}
