package transaction.service.weixin.dto.authorizationplatform.authorizeroption;

/**
 * Created by zhengjianhui on 17/5/11.
 */
public class AuthorizerOptionResponse {

    private String authorizer_appid;

    private String option_name;

    private String option_value;

    public String getAuthorizer_appid() {
        return authorizer_appid;
    }

    public void setAuthorizer_appid(String authorizer_appid) {
        this.authorizer_appid = authorizer_appid;
    }

    public String getOption_name() {
        return option_name;
    }

    public void setOption_name(String option_name) {
        this.option_name = option_name;
    }

    public String getOption_value() {
        return option_value;
    }

    public void setOption_value(String option_value) {
        this.option_value = option_value;
    }
}