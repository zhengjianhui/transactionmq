package transaction.service.weixin.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import org.apache.commons.lang3.StringUtils;

import transaction.converter.EnumCodeGetter;

/**
 * Created by zhengjianhui on 17/5/11.
 */
public enum OptionName implements EnumCodeGetter {
    location_report("1", "location_report"),
    voice_recognize("2", "voice_recognize"),
    customer_service("3", "customer_service");

    /**
     * 存到数据库的编码
     */
    private String code;

    /**
     * 描述信息
     */
    private String description;

    OptionName(String code, String description) {
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
    @JsonValue
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
    public static OptionName getByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }

        for (OptionName element : OptionName.values()) {
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
    public static OptionName getByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }

        for (OptionName element : OptionName.values()) {
            if (element.name().equals(StringUtils.upperCase(name))) {
                return element;
            }
        }

        return null;
    }
}
