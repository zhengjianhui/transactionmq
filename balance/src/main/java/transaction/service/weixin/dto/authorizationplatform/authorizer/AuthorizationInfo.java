package transaction.service.weixin.dto.authorizationplatform.authorizer;

import java.util.List;

import transaction.service.weixin.dto.authorizationplatform.authorization.Funcscope;

/**
 * Created by zhengjianhui on 17/5/11.
 */
public class AuthorizationInfo {

    private String appid;

    List<Funcscope> func_info;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public List<Funcscope> getFunc_info() {
        return func_info;
    }

    public void setFunc_info(List<Funcscope> func_info) {
        this.func_info = func_info;
    }

    @Override
    public String toString() {
        return "AuthorizationInfo{" +
                "appid='" + appid + '\'' +
                ", func_info=" + func_info +
                '}';
    }
}
