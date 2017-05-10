package transaction.service.balance;

import java.math.BigDecimal;

import transaction.domain.balance.Balance;

/**
 * Created by zhengjianhui on 17/5/7.
 */
public interface BalanceServer {

    Balance addBalance(Balance balance);

    Balance updateAmount(String userid, BigDecimal money, String balance_mode);

    void updateBySelectivePay(Balance balance);

    void remove(String userId);
}
