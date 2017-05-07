package transaction.service.pay;

import transaction.domain.pay.Pay;

/**
 * Created by zhengjianhui on 17/5/7.
 */
public interface PayService {

    Pay addPay(Pay pay);

    void updateBySelectivePay(Pay pay);

    void remove(String userId);
}
