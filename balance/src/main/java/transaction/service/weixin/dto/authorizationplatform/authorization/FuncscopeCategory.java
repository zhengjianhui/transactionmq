package transaction.service.weixin.dto.authorizationplatform.authorization;

/**
 * Created by zhengjianhui on 17/5/10.
 */
public class FuncscopeCategory {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FuncscopeCategory{" +
                "id='" + id + '\'' +
                '}';
    }
}
