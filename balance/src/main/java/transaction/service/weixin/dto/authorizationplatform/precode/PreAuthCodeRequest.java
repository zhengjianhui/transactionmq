package transaction.service.weixin.dto.authorizationplatform.precode;

/**
 * Created by zhengjianhui on 17/5/10.
 */
public class PreAuthCodeRequest {

    private String component_appid;

    public String getComponent_appid() {
        return component_appid;
    }

    public void setComponent_appid(String component_appid) {
        this.component_appid = component_appid;
    }
}
