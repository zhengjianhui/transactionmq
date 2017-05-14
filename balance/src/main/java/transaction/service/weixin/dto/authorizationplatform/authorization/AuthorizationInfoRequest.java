package transaction.service.weixin.dto.authorizationplatform.authorization;

/**
 * Created by zhengjianhui on 17/5/10.
 */
public class AuthorizationInfoRequest {

    private String component_appid;

    private String authorization_code;

    public String getComponent_appid() {
        return component_appid;
    }

    public void setComponent_appid(String component_appid) {
        this.component_appid = component_appid;
    }

    public String getAuthorization_code() {
        return authorization_code;
    }

    public void setAuthorization_code(String authorization_code) {
        this.authorization_code = authorization_code;
    }
}
