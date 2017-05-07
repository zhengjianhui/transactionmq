package transaction.service.pay.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Pay addPay(Pay pay) {
        payMapper.insert(pay);

        return pay;
    }

    public void updateBySelectivePay(Pay pay) {
        int cnt = payMapper.updateByPrimaryKeySelective(pay);
        if(cnt != 1) {
            // 未能更新到记录
        }

    }

    public void remove(String userId) {
        int cnt = payMapper.deleteByPrimaryKey(userId);
        if(cnt != 1) {
            // 数据不存在未能删除数据
        }
    }
}
