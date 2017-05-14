package transaction.service.weixin.dto.authorizationplatform.authorizeroption;

/**
 * Created by zhengjianhui on 17/5/11.
 */
public class UpdateAuthorizerOptionResponse {

    private String errcode;

    private String errmsg;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
