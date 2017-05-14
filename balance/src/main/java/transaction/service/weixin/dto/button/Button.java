package transaction.service.weixin.dto.button;

/**
 * Created by zhengjianhui on 17/5/12.
 */
public class Button {

    private String name;

    private SubButton sub_button;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SubButton getSub_button() {
        return sub_button;
    }

    public void setSub_button(SubButton sub_button) {
        this.sub_button = sub_button;
    }

    @Override
    public String toString() {
        return "Button{" +
                "name='" + name + '\'' +
                ", sub_button=" + sub_button +
                '}';
    }
}
