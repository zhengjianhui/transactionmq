package transaction.controller.balance;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by zhengjianhui on 17/5/14.
 */
@XmlRootElement(name="test")
public class TestXmlBean {

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
