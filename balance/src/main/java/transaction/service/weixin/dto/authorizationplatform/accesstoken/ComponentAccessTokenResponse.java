package transaction.service.weixin.dto.authorizationplatform.accesstoken;

/**
 * Created by zhengjianhui on 17/5/10.
 */
public class ComponentAccessTokenResponse {

    private String component_access_token;

    private String expires_in;

    public String getComponent_access_token() {
        return component_access_token;
    }

    public void setComponent_access_token(String component_access_token) {
        this.component_access_token = component_access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }
}
