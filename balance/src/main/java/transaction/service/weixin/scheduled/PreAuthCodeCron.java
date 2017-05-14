package transaction.service.weixin.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import transaction.service.weixin.WeixinPlatformService;

/**
 * Created by zhengjianhui on 17/5/11.
 */
@Component
public class PreAuthCodeCron {
    @Autowired
    WeixinPlatformService weixinPlatformService;

    @Scheduled(cron="0 0/18 * * * ?")
    public void job() {
        System.out.println("执行定时任务");
        weixinPlatformService.setPRE_AUTH_CODE();

        System.err.println("更新PRE_AUTH_CODE成功");
    }
}
