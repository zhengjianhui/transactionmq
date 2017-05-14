package transaction.service.weixin.dto.button;

import java.util.List;

/**
 * Created by zhengjianhui on 17/5/12.
 */
public class NewsInfo {

    List<NewsInfoDetail> list;

    public List<NewsInfoDetail> getList() {
        return list;
    }

    public void setList(List<NewsInfoDetail> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "NewsInfo{" +
                "list=" + list +
                '}';
    }
}
