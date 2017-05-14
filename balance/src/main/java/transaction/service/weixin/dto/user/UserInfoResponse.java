package transaction.service.weixin.dto.user;

import java.util.List;

/**
 * Created by zhengjianhui on 17/5/13.
 */
public class UserInfoResponse {


    /**
     * openid : ojTEdv62ytwxQCFZNHGpIeDDurIc
     * nickname : 叶鸣
     * sex : 1
     * language : zh_CN
     * city : 昆明
     * province : 云南
     * country : 中国
     * headimgurl : http://wx.qlogo.cn/mmopen/uSiczMDLzwEngCSCEKB0fMUE8RUBL66FK6dpKEBzy6DnUiaHcMviaCqGGlyPxe3lQFBmqJibDc3iccBcJDyTI4EeHHSoAZtmL8HiaY/0
     * privilege : []
     */

    private String openid;
    private String nickname;
    private String sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private List<?> privilege;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public List<?> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<?> privilege) {
        this.privilege = privilege;
    }
}
