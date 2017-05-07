package transaction.service.balance.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import transaction.dao.mybatis.balance.BalanceMapper;
import transaction.domain.balance.Balance;
import transaction.service.balance.BalanceServer;

/**
 * Created by zhengjianhui on 17/5/7.
 */
@Service
public class BalanceServerImpl implements BalanceServer {

    @Autowired
    private BalanceMapper balanceMapper;

    public Balance addBalance(Balance balance) {
        balanceMapper.insert(balance);
        return balance;
    }

    public void updateBySelectivePay(Balance balance) {
        int cnt = balanceMapper.updateByPrimaryKeySelective(balance);
        if(cnt != 1) {
            // 未能更新到记录
        }
    }

    public void remove(String userId) {
        int cnt = balanceMapper.deleteByPrimaryKey(userId);
        if(cnt != 1) {
            // 数据不存在未能删除数据
        }
    }
}
