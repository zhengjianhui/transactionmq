package transaction.service.weixin.dto.button;

/**
 * Created by zhengjianhui on 17/5/12.
 */
public class SubButtonDetail {

    private String type;
    private String name;

    private String url;
    private String value;
    private String key;

    NewsInfo news_info;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public NewsInfo getNews_info() {
        return news_info;
    }

    public void setNews_info(NewsInfo news_info) {
        this.news_info = news_info;
    }

    @Override
    public String toString() {
        return "SubButton{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", value='" + value + '\'' +
                ", key='" + key + '\'' +
                ", news_info=" + news_info +
                '}';
    }
}
