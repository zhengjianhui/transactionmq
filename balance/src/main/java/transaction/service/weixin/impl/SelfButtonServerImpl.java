package transaction.service.weixin.impl;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import transaction.service.weixin.SelfButtonServer;
import transaction.service.weixin.WeixinPlatformService;
import transaction.service.weixin.dto.button.Button;
import transaction.service.weixin.dto.button.ButtonResponse;
import transaction.service.weixin.utils.OkhttpUtil;
/**
 * Created by zhengjianhui on 17/5/12.
 */
@Service
public class SelfButtonServerImpl implements SelfButtonServer {

    private static final String CURRENT_SELFMENU_INFO_URL = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=";


    @Autowired
    private WeixinPlatformService weixinPlatformService;


    @Override
    public ButtonResponse getSelfButtonInfo() {
        String response = OkhttpUtil.get(CURRENT_SELFMENU_INFO_URL + weixinPlatformService.getAUTHORIZER_ACCESS_TOKEN(), Boolean.TRUE);
        Gson gson = new Gson();
        ButtonResponse buttonResponse = gson.fromJson(response, ButtonResponse.class);
        return buttonResponse;
    }

//    @Override
    public static void setSelfButtonInfo(ButtonResponse selfButtonInfo) throws Exception {
        List<Button> buttons = selfButtonInfo.getSelfmenu_info().getButton();
        if(buttons.size() > 3) {
            throw new Exception("一级菜单数过多");
        }

        for (Button button : buttons) {
            if (button.getSub_button().getList().size() > 5) {
                throw new Exception("二级菜单数过多");
            }
        }
    }


    public static void main(String[] args) throws Exception {

        String accessToken = "QV0GGL9yK-y6d7nNoG-0_RxzubLyQg3h4zmIPS6GeBm8PtegoNj-xqynec5sk2zLzRbuHkUpOHPWje9ehOO_0RkNKKxKXVEwzpdMOwaaurWV1PRhoTVtJjvZjwDp7Av-SGOhAFDOCK";
        String response = OkhttpUtil.get(CURRENT_SELFMENU_INFO_URL + accessToken, Boolean.TRUE);
        Gson gson = new Gson();
        ButtonResponse buttonResponse = gson.fromJson(response, ButtonResponse.class);

        buttonResponse.getSelfmenu_info().getButton().add(new Button());
        setSelfButtonInfo(buttonResponse);

    }



}
