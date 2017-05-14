package transaction.service.weixin.dto.authorizationplatform.authorizertoken;

/**
 * Created by zhengjianhui on 17/5/10.
 */
public class UpdateAuthorizerRefreshTokenResponse {

    private String authorizer_access_token;

    private String expires_in;

    private String authorizer_refresh_token;

    public String getAuthorizer_access_token() {
        return authorizer_access_token;
    }

    public void setAuthorizer_access_token(String authorizer_access_token) {
        this.authorizer_access_token = authorizer_access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getAuthorizer_refresh_token() {
        return authorizer_refresh_token;
    }

    public void setAuthorizer_refresh_token(String authorizer_refresh_token) {
        this.authorizer_refresh_token = authorizer_refresh_token;
    }

    @Override
    public String toString() {
        return "UpdateAuthorizerRefreshTokenResponse{" +
                "authorizer_access_token='" + authorizer_access_token + '\'' +
                ", expires_in='" + expires_in + '\'' +
                ", authorizer_refresh_token='" + authorizer_refresh_token + '\'' +
                '}';
    }
}
