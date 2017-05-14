package transaction.service.weixin.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import transaction.service.weixin.WeixinPlatformService;

/**
 * Created by zhengjianhui on 17/5/11.
 */
@Component
public class CompomentAccessTokenCron {

    @Autowired
    WeixinPlatformService weixinPlatformService;


    @Scheduled(cron="0 0/30 * * * ?")
    @Scheduled(cron="0 0 */1 * * ?")
    public void job() {
        System.out.println("执行定时任务");
        weixinPlatformService.setCOMPONENT_ACCESS_TOKEN();

        System.err.println("更新COMPONENT_ACCESS_TOKEN成功");
    }
}
