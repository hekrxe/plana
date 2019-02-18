package com.hekrxe.gts.account.service;

import com.hekrxe.gts.api.AccountService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author tanhuayou on 2019/02/18
 */
@Service(version = "${service.version.account}")
public class AccountServiceImpl implements AccountService {
    @Override
    public void debit(String userId, int money) {

    }
}
