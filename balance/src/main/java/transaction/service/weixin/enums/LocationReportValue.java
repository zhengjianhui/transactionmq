package transaction.service.weixin.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by zhengjianhui on 17/5/11.
 */
public enum LocationReportValue implements Value {
    NO_REPORT("0", "无上报"),
    REPORT("1", "进入会话时上报"),
    TIMING("2", "每5s上报");


    /**
     * 存到数据库的编码
     */
    private String code;

    /**
     * 描述信息
     */
    private String description;

    LocationReportValue(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * spring mvc 处理枚举的注解
     * @return
     */
    public String getCode() {
        return this.code;
    }

    public String toString() {
        return this.code + "＝" + this.description;
    }

    /**
     *
     * 功能描述:根据编码获取对应的枚举对象 <br>
     * 〈功能详细描述〉
     *
     * @param code
     * @return 返回对应的枚举类对象，匹配不上则返回null
     */
    public static LocationReportValue getByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }

        for (LocationReportValue element : LocationReportValue.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }

        return null;
    }

    /**
     * 根据名称获取对应的枚举对象
     *
     * @param name 名称
     * @return
     */
    public static LocationReportValue getByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }

        for (LocationReportValue element : LocationReportValue.values()) {
            if (element.name().equals(StringUtils.upperCase(name))) {
                return element;
            }
        }

        return null;
    }
}
