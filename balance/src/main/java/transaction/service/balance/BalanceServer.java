package transaction.service.balance;

import transaction.domain.balance.Balance;

/**
 * Created by zhengjianhui on 17/5/7.
 */
public interface BalanceServer {

    Balance addBalance(Balance balance);

    void updateBySelectivePay(Balance balance);

    void remove(String userId);
}
