package transaction.service.weixin.dto.user;

/**
 * Created by zhengjianhui on 17/5/13.
 */
public class UserTokenResponse {


    /**
     * access_token : ycOImjNVwvjAC6Yfzsk7JnL0wZTEHtIigheDpZ7AptKNPGW_qkmNuQml7WCDPwYNTok97INthT9ZS1nYeq8PanLLorzwj1wJ8GZjTMTEUfmuqK3vlnMJrM0ut9GXRmIJ
     * expires_in : 7200
     * refresh_token : rCM9CtGNYdxTbNUf6ivupYUMcvhiYp5p1J58q-j37xUtAqP31kYPW-TDf-4CKtZgaMiuEns6dbBYdwdZfI8Td-j8ob4lskoEiGyMJzzm-980FqUkkbejYbOyJKDFASys
     * openid : ojTEdv62ytwxQCFZNHGpIeDDurIc
     * scope : snsapi_base
     */

    private String access_token;
    private String expires_in;
    private String refresh_token;
    private String openid;
    private String scope;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
