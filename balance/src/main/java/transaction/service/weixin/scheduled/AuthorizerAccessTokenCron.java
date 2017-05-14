package transaction.service.weixin.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import transaction.service.weixin.WeixinPlatformService;

/**
 * Created by zhengjianhui on 17/5/11.
 */
@Component
public class AuthorizerAccessTokenCron {

    @Autowired
    WeixinPlatformService weixinPlatformService;


    @Scheduled(cron="0 0/30 * * * ?")
    @Scheduled(cron="0 0 */1 * * ?")
    public void job() {
        System.out.println("执行定时任务");
        weixinPlatformService.updateAUTHORIZER_REFRESH_TOKEN();

        System.err.println("更新AUTHORIZER_REFRESH_TOKEN");
    }
}
