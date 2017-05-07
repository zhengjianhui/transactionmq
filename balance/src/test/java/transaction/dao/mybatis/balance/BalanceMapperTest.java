package transaction.dao.mybatis.balance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import transaction.domain.balance.Balance;

/**
 * Created by zhengjianhui on 17/5/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:/spring/spring-dao.xml"})
public class BalanceMapperTest {

    @Autowired
    private BalanceMapper balanceMapper;

    @Test
    public void insert() throws Exception {
        System.out.println(balanceMapper);

        Balance record = new Balance();
        record.setUserid("1234516");
        record.setUsername("java 真几把啰嗦");
        balanceMapper.insert(record);
    }

}