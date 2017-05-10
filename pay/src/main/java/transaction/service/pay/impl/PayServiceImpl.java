package transaction.service.pay.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import transaction.dao.mybatis.pay.PayMapper;
import transaction.domain.pay.Pay;
import transaction.service.pay.PayService;

/**
 * Created by zhengjianhui on 17/5/7.
 */
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private PayMapper payMapper;

    @Override
    public Pay addPay(Pay pay) {
        payMapper.insert(pay);

        return pay;
    }

    @Override
    @Transactional
    public Pay updateAmount(String userid, BigDecimal money, String pay_mode) {
        Pay pay = payMapper.selectByPrimaryKey(userid);
        if ("in".equals(pay_mode)) {
            pay.setAmount(pay.getAmount().add(money));
        } else {
            pay.setAmount(pay.getAmount().subtract(money));
        }

        pay.setUpdateTime(LocalDateTime.now());

        updateBySelectivePay(pay);

        // 认为制造异常
//        int i = 1 / 0;
        return pay;
    }

    @Override
    public void updateBySelectivePay(Pay pay) {
        int cnt = payMapper.updateByPrimaryKeySelective(pay);
        if (cnt != 1) {
            // 未能更新到记录
        }

    }

    @Override
    public void remove(String userId) {
        int cnt = payMapper.deleteByPrimaryKey(userId);
        if (cnt != 1) {
            // 数据不存在未能删除数据
        }
    }
}
