package transaction.dao.mybatis.pay;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import transaction.domain.pay.Pay;

/**
 * Created by zhengjianhui on 17/5/7.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:/spring/spring-dao.xml"})
public class PayMapperTest {

    @Autowired
    private PayMapper payMapper;

    @Test
    public void insert() throws Exception {
        Pay record = new Pay();
        record.setUserid("123123");
        record.setUsername("asdas");

        payMapper.insert(record);
    }

}