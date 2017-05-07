package transaction.controller.balance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import transaction.domain.balance.Balance;
import transaction.service.balance.BalanceServer;

/**
 * Created by zhengjianhui on 17/5/7.
 */
@RestController
public class BalanceController {

    @Autowired
    private BalanceServer balanceServer;

    @RequestMapping(value = "/balances", method = RequestMethod.POST)
    public Balance addBalance(@RequestBody Balance balance) {
        return balanceServer.addBalance(balance);
    }
}
