package transaction.service.weixin.dto.authorizationplatform.precode;

/**
 * Created by zhengjianhui on 17/5/10.
 */
public class PreAuthCodeResponse {

    private String pre_auth_code;

    private String expires_in;

    public String getPre_auth_code() {
        return pre_auth_code;
    }

    public void setPre_auth_code(String pre_auth_code) {
        this.pre_auth_code = pre_auth_code;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }
}
