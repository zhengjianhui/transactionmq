package transaction.service.weixin.dto.authorizationplatform.jsticket;

/**
 * Created by zhengjianhui on 17/5/10.
 */
public class JsTicketResponse {

    private String errcode;

    private String errmsg;

    private String ticket;

    private String expires_in;

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

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }
}
