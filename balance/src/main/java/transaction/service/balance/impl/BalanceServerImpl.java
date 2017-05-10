package transaction.service.balance.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import transaction.dao.mybatis.balance.BalanceMapper;
import transaction.domain.balance.Balance;
import transaction.service.balance.BalanceServer;

/**
 * Created by zhengjianhui on 17/5/7.
 */
@Service("balanceServer")
public class BalanceServerImpl implements BalanceServer {

    @Autowired
    private BalanceMapper balanceMapper;

    @Override
    public Balance addBalance(Balance balance) {
        balanceMapper.insert(balance);
        return balance;
    }


    @Override
    @Transactional
    public Balance updateAmount(String userid, BigDecimal money, String balance_mode) {
        Balance balance = balanceMapper.selectByPrimaryKey(userid);
        if("in".equals(balance_mode)) {
            balance.setAmount(balance.getAmount().add(money));
        } else {
            balance.setAmount(balance.getAmount().subtract(money));
        }

        balance.setUpdateTime(LocalDateTime.now());
        updateBySelectivePay(balance);

        return balance;

    }

    @Override
    public void updateBySelectivePay(Balance balance) {
        int cnt = balanceMapper.updateByPrimaryKeySelective(balance);
        if(cnt != 1) {
            // 未能更新到记录
        }
    }

    @Override
    public void remove(String userId) {
        int cnt = balanceMapper.deleteByPrimaryKey(userId);
        if(cnt != 1) {
            // 数据不存在未能删除数据
        }
    }
}
