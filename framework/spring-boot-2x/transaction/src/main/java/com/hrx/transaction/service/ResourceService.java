package com.hrx.transaction.service;

import com.hrx.transaction.dao.ResourceDAO;
import com.hrx.transaction.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * @author tanhuayou on 2019/05/07
 */
@Service
public class ResourceService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ResourceDAO resourceDAO;

    public long insert() throws Exception {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Resource resource = new Resource()
                .setAppId(1L).setStaffId(0L)
                .setName("testName").setUrl("testUrl")
                .setSize(10L).setDbCreateTime(timestamp).setDbUpdateTime(timestamp);
        resourceDAO.insert(resource);

        Long id = resource.getId();
        if ((id & 1) == 0) {
            logger.info("id=" + id);
            throw new RuntimeException("Ex");
        }

        return id;
    }

    @Transactional(rollbackFor = Throwable.class)
    public long required() throws Exception {
        return insert();
    }

}
