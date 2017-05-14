package transaction.service.weixin.enums;

import transaction.converter.EnumCodeGetter;

/**
 * Created by zhengjianhui on 17/5/10.
 */
public interface InfoType extends EnumCodeGetter {
    String AUTHORIZED = "authorized";

    String UPDATEAUTHORIZED = "updateauthorized";

    String UNAUTHORIZED = "unauthorized";

    String COMPONENT_VERIFY_TICKET = "component_verify_ticket";
}
