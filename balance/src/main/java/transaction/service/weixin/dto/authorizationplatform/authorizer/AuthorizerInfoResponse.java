package transaction.service.weixin.dto.authorizationplatform.authorizer;

/**
 * Created by zhengjianhui on 17/5/11.
 */
public class AuthorizerInfoResponse {

    private AuthorizerInfo authorizer_info;

    private AuthorizationInfo authorization_info;

    public AuthorizerInfo getAuthorizer_info() {
        return authorizer_info;
    }

    public void setAuthorizer_info(AuthorizerInfo authorizer_info) {
        this.authorizer_info = authorizer_info;
    }

    public AuthorizationInfo getAuthorization_info() {
        return authorization_info;
    }

    public void setAuthorization_info(AuthorizationInfo authorization_info) {
        this.authorization_info = authorization_info;
    }
}
