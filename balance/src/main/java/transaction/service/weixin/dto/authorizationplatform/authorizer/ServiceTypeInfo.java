package transaction.service.weixin.dto.authorizationplatform.authorizer;

/**
 * Created by zhengjianhui on 17/5/11.
 */
public class ServiceTypeInfo {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ServiceTypeInfo{" +
                "id='" + id + '\'' +
                '}';
    }
}
