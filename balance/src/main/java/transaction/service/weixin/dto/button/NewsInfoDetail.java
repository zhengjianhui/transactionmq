package transaction.service.weixin.dto.button;

/**
 * Created by zhengjianhui on 17/5/12.
 */
public class NewsInfoDetail {

    private String title;

    private String author;

    private String digest;

    private String show_cover;

    private String cover_url;

    private String content_url;

    private String source_url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getShow_cover() {
        return show_cover;
    }

    public void setShow_cover(String show_cover) {
        this.show_cover = show_cover;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    @Override
    public String toString() {
        return "NewsInfo{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", digest='" + digest + '\'' +
                ", show_cover='" + show_cover + '\'' +
                ", cover_url='" + cover_url + '\'' +
                ", content_url='" + content_url + '\'' +
                ", source_url='" + source_url + '\'' +
                '}';
    }
}
