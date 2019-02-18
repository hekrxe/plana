package com.hekrxe.gts.storage.service;

import com.hekrxe.gts.api.StorageService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author tanhuayou on 2019/02/18
 */
@Service
public class StorageServiceImpl implements StorageService {
    @Override
    public void deduct(String commodityCode, int count) {

    }
}
