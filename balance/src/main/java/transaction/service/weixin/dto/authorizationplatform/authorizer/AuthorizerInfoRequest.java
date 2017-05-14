package transaction.service.weixin.dto.authorizationplatform.authorizer;

/**
 * Created by zhengjianhui on 17/5/11.
 */
public class AuthorizerInfoRequest {

    private String component_appid;

    private String authorizer_appid;

    public String getComponent_appid() {
        return component_appid;
    }

    public void setComponent_appid(String component_appid) {
        this.component_appid = component_appid;
    }

    public String getAuthorizer_appid() {
        return authorizer_appid;
    }

    public void setAuthorizer_appid(String authorizer_appid) {
        this.authorizer_appid = authorizer_appid;
    }
}
