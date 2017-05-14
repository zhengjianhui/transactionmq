package transaction.service.weixin.dto.button;

import java.util.List;

/**
 * Created by zhengjianhui on 17/5/12.
 */
public class SelfmenuInfo {

    List<Button> button;

    public List<Button> getButton() {
        return button;
    }

    public void setButton(List<Button> button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "SelfmenuInfo{" +
                "button=" + button +
                '}';
    }
}
