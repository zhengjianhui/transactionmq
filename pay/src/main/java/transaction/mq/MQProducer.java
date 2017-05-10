package transaction.mq;

import com.alibaba.rocketmq.client.QueryResult;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.LocalTransactionExecuter;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.client.producer.TransactionMQProducer;
import com.alibaba.rocketmq.client.producer.TransactionSendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by zhengjianhui on 17/5/8.
 * 生产者
 */
@Component
public class MQProducer {

    private final String GROUP_NAME = "transaction_producer";

    private final String NAMESERVER_ADDR = "10.211.55.12:9876;10.211.55.13:9876;10.211.55.14:9876;10.211.55.15:9876";

    private final TransactionMQProducer producer;

    public MQProducer() {
        this.producer = new TransactionMQProducer(GROUP_NAME);
        this.producer.setNamesrvAddr(NAMESERVER_ADDR);

        // 检查请求的线层
        this.producer.setCheckRequestHoldMax(5);
        // 事物回查并发设置
        this.producer.setCheckThreadPoolMaxSize(2000);
        this.producer.setCheckThreadPoolMinSize(20);
        this.producer.setTransactionCheckListener(new TransactionCheckListener() {
            @Override
            public LocalTransactionState checkLocalTransactionState(MessageExt messageExt) {
                System.out.println("state: " + messageExt.getBody());

                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });

        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
            System.err.println("transaction_producer start failed!");
        }
    }


    public QueryResult queryMessage(String topic, String key, int maxNum, long begin, long end) throws MQClientException, InterruptedException {
        return this.producer.queryMessage(topic, key, maxNum, begin, end);
    }


    /**
     * 由于阿里巴巴闭源，该方法不好用(3.08可用)
     */
    public LocalTransactionState check(MessageExt msg) {
        LocalTransactionState ls = this.producer.getTransactionCheckListener().checkLocalTransactionState(msg);
        return ls;
    }

    /**
     * @param message                  消息对象
     * @param localTransactionExecuter 自定义本地事物
     * @param transactionMap           附加参数(可以传递进本地事物使用)
     */
    public void sendTransactionMessage(Message message, LocalTransactionExecuter localTransactionExecuter, Map<String, Object> transactionMap) throws MQClientException {
        TransactionSendResult result = this.producer.sendMessageInTransaction(message, localTransactionExecuter, transactionMap);
        System.out.println("send返回内容：" + result.toString());

    }


    public void shutdown() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                producer.shutdown();
            }
        }));

        System.exit(0);
    }
}
