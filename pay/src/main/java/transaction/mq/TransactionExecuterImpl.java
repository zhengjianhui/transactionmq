package transaction.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.producer.LocalTransactionExecuter;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.common.message.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

import transaction.service.pay.PayService;

/**
 * Created by zhengjianhui on 17/5/8.
 * 本地事物执行器
 */
@Component
public class TransactionExecuterImpl implements LocalTransactionExecuter {

    @Autowired
    PayService payService;

    @Override
    public LocalTransactionState executeLocalTransactionBranch(Message message, Object o) {
        try {
            JSONObject body = JSON.parseObject(new String(message.getBody(), "utf-8"));
            Map<String, Object> mapArgs = (Map<String, Object>) o;
            // ----------------IN PUT -----------------
            System.out.println("收到消息：" + message.getBody());
            System.out.println("附加参数：" + mapArgs);
            System.out.println("标签：" + message.getTags());

            System.out.println("attr1" + mapArgs.get("attr1"));
            System.out.println("attr2" + mapArgs.get("attr2"));
            // ----------------IN PUT -----------------

            String userid = body.getString("userid");
            BigDecimal money = body.getBigDecimal("money");
            String pay_mode = body.getString("pay_mode");

            payService.updateAmount(userid, money, pay_mode);

            // 模拟unknow  人为制造一条 记录
//            return LocalTransactionState.UNKNOW;
            return LocalTransactionState.COMMIT_MESSAGE;

        } catch (Exception e) {
            e.printStackTrace();
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }

    }
}
