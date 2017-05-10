package transaction.service.pay;

import java.math.BigDecimal;

import transaction.domain.pay.Pay;

/**
 * Created by zhengjianhui on 17/5/7.
 */
public interface PayService {

    Pay addPay(Pay pay);

    Pay updateAmount(String userid, BigDecimal money, String pay_mode);

    void updateBySelectivePay(Pay pay);

    void remove(String userId);
}
