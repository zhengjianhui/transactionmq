package transaction.service.weixin.dto.button;

/**
 * Created by zhengjianhui on 17/5/12.
 */
public class ButtonResponse {

    /**
     * 菜单是否开启，0代表未开启，1代表开启
     */
    private String is_menu_open;

    /**
     * 菜单信息
     */
    private SelfmenuInfo selfmenu_info;

    public String getIs_menu_open() {
        return is_menu_open;
    }

    public void setIs_menu_open(String is_menu_open) {
        this.is_menu_open = is_menu_open;
    }

    public SelfmenuInfo getSelfmenu_info() {
        return selfmenu_info;
    }

    public void setSelfmenu_info(SelfmenuInfo selfmenu_info) {
        this.selfmenu_info = selfmenu_info;
    }

    @Override
    public String toString() {
        return "ButtonResponse{" +
                "is_menu_open='" + is_menu_open + '\'' +
                ", selfmenu_info=" + selfmenu_info +
                '}';
    }
}
