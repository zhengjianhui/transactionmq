package transaction.service.weixin;

import transaction.service.weixin.dto.button.ButtonResponse;

/**
 * Created by zhengjianhui on 17/5/12.
 */
public interface SelfButtonServer {

    ButtonResponse getSelfButtonInfo();

//    void setSelfButtonInfo(ButtonResponse selfButtonInfo) throws Exception;
}
