package transaction.service.weixin.dto.button;

import java.util.List;

/**
 * Created by zhengjianhui on 17/5/12.
 */
public class SubButton {

    private List<SubButtonDetail> list;

    public List<SubButtonDetail> getList() {
        return list;
    }

    public void setList(List<SubButtonDetail> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "SubButton{" +
                "list=" + list +
                '}';
    }
}
