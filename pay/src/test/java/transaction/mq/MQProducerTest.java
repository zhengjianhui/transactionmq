package transaction.mq;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.common.message.Message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zhengjianhui on 17/5/8.
 */
@ContextConfiguration({"classpath*:/spring/spring-dao.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MQProducerTest {

    @Autowired
    MQProducer mqProducer;

    @Autowired
    TransactionExecuterImpl transactionExecuter;

    @Test
    public void sendTransactionMessage() throws Exception {
        Message message = new Message();
        message.setTopic("pay");
        message.setTags("tag");
        message.setKeys(UUID.randomUUID().toString());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userid", "z3");
        jsonObject.put("money", "1000");
        jsonObject.put("pay_mode", "out");
        jsonObject.put("balance_mode", "in");

        message.setBody(jsonObject.toJSONString().getBytes());

        Map<String, Object> attr = new HashMap<>();
        attr.put("attr1", "111");
        attr.put("attr2", "222");

        // 发送消息，本地事物， 附带条件
        this.mqProducer.sendTransactionMessage(message, transactionExecuter, attr);

    }

}