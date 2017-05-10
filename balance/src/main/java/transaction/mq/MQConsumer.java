package transaction.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.QueryResult;
import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

import transaction.service.balance.BalanceServer;

/**
 * Created by zhengjianhui on 17/5/10.
 */
@Component
public class MQConsumer {
    private final String GROUP_NAME = "transaction_consumer";

    private final String NAMESERVER_ADDR = "10.211.55.12:9876;10.211.55.13:9876;10.211.55.14:9876;10.211.55.15:9876";

    private final DefaultMQPushConsumer consumer;

    @Autowired
    private BalanceServer balanceServer;

    public MQConsumer() {
        this.consumer = new DefaultMQPushConsumer(GROUP_NAME);
        try {
            this.consumer.setNamesrvAddr(NAMESERVER_ADDR);
            this.consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            this.consumer.subscribe("pay", "*");
            this.consumer.registerMessageListener(new Listener());
            this.consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }

    }


    class Listener implements MessageListenerConcurrently {

        @Override
        public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
            MessageExt msg = list.get(0);

            try {
                String topic = msg.getTopic();
                JSONObject body = JSON.parseObject(new String(msg.getBody(), "utf-8"));
                String tags = msg.getTags();
                String keys = msg.getTags();

                System.out.println("balance 服务收到消息， key: " + keys + "body: " + body + "tag: " + tags);

                String userid = body.getString("userid");
                BigDecimal money = body.getBigDecimal("money");
                String balance_mode = body.getString("balance_mode");

                // 业务处理
                balanceServer.updateAmount(userid, money, balance_mode);


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                if(msg.getReconsumeTimes() == 3) {
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    // 此处记录日志
                }

                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }

            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
    }

    public QueryResult queryMessage(String topic, String key, int maxNum, long begnin, long end) throws MQClientException, InterruptedException {
        long current = System.currentTimeMillis();
        return this.consumer.queryMessage(topic, key, maxNum, begnin, end);
    }
}
