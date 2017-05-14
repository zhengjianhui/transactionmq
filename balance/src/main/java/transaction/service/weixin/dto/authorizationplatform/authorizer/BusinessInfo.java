package transaction.service.weixin.dto.authorizationplatform.authorizer;

/**
 * Created by zhengjianhui on 17/5/11.
 */
public class BusinessInfo {

    private String open_store;

    private String open_scan;

    private String open_pay;

    private String open_card;

    private String open_shake;

    public String getOpen_store() {
        return open_store;
    }

    public void setOpen_store(String open_store) {
        this.open_store = open_store;
    }

    public String getOpen_scan() {
        return open_scan;
    }

    public void setOpen_scan(String open_scan) {
        this.open_scan = open_scan;
    }

    public String getOpen_pay() {
        return open_pay;
    }

    public void setOpen_pay(String open_pay) {
        this.open_pay = open_pay;
    }

    public String getOpen_card() {
        return open_card;
    }

    public void setOpen_card(String open_card) {
        this.open_card = open_card;
    }

    public String getOpen_shake() {
        return open_shake;
    }

    public void setOpen_shake(String open_shake) {
        this.open_shake = open_shake;
    }

    @Override
    public String toString() {
        return "BusinessInfo{" +
                "open_store='" + open_store + '\'' +
                ", open_scan='" + open_scan + '\'' +
                ", open_pay='" + open_pay + '\'' +
                ", open_card='" + open_card + '\'' +
                ", open_shake='" + open_shake + '\'' +
                '}';
    }
}
