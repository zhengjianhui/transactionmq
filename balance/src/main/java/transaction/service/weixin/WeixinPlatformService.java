package transaction.service.weixin;

import transaction.service.weixin.dto.authorizationplatform.authorizer.AuthorizerInfoResponse;
import transaction.service.weixin.dto.authorizationplatform.authorizeroption.AuthorizerOptionResponse;
import transaction.service.weixin.dto.authorizationplatform.authorizeroption.UpdateAuthorizerOptionResponse;
import transaction.service.weixin.enums.OptionName;
import transaction.service.weixin.enums.Value;

/**
 * Created by zhengjianhui on 17/5/9.
 */
public interface WeixinPlatformService {

    void setJsapiTicket();

    String getJsapiTicket();

    void authorizationEvent(String msg_signature, String timestamp,   String nonce, String fromXML);

    String getCOMPONENT_VERIFY_TICKET();

    void setCOMPONENT_VERIFY_TICKET(String ComponentVerifyTicket);

    String getCOMPONENT_ACCESS_TOKEN();

    void setCOMPONENT_ACCESS_TOKEN();

    String getPRE_AUTH_CODE();

    void setPRE_AUTH_CODE();

    String getAUTH_CODE();

    void setAUTH_CODE(String AUTH_CODE);

    String getAUTHORIZER_ACCESS_TOKEN();

    void setAUTHORIZER_ACCESS_TOKEN();

    String getAUTHORIZER_REFRESH_TOKEN();

    void updateAUTHORIZER_REFRESH_TOKEN();

    void authorizerInfo();

    AuthorizerInfoResponse getAuthorizerUserInfo();

    AuthorizerOptionResponse getAuthorizerOption(OptionName name);

    UpdateAuthorizerOptionResponse updateAuthorizerOption(OptionName name, Value value);

    String getAuthorizerAppid();

}
