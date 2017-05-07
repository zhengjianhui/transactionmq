package transaction.controller.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import transaction.domain.pay.Pay;
import transaction.service.pay.PayService;

/**
 * Created by zhengjianhui on 17/5/7.
 */
@RestController
public class PayController {

    @Autowired
    private PayService payService;

    @RequestMapping(value = "/pays", method = RequestMethod.POST)
    public Pay addPay(@RequestBody Pay pay) {
        return payService.addPay(pay);
    }
}
