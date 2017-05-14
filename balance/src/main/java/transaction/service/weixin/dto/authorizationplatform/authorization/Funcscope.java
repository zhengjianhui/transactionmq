package transaction.service.weixin.dto.authorizationplatform.authorization;

/**
 * Created by zhengjianhui on 17/5/10.
 */
public class Funcscope {

    private FuncscopeCategory funcscope_category;

    public FuncscopeCategory getFuncscope_category() {
        return funcscope_category;
    }

    public void setFuncscope_category(FuncscopeCategory funcscope_category) {
        this.funcscope_category = funcscope_category;
    }

    @Override
    public String toString() {
        return "Funcscope{" +
                "funcscope_category=" + funcscope_category +
                '}';
    }
}
