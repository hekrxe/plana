
package com.hrx.transaction.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Resource
 * <p>
 * 2018-03-05 11:15:29
 *
 * @author KFC
 */
public class Resource implements Serializable {
    private Long id;
    private Long appId;
    private Long staffId;
    private String name;
    private Long size;
    private String url;
    private Timestamp dbUpdateTime;
    private Timestamp dbCreateTime;

    public Long getId() {
        return id;
    }

    public Resource setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getAppId() {
        return appId;
    }

    public Resource setAppId(Long appId) {
        this.appId = appId;
        return this;
    }

    public Long getStaffId() {
        return staffId;
    }

    public Resource setStaffId(Long staffId) {
        this.staffId = staffId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Resource setName(String name) {
        this.name = name;
        return this;
    }

    public Long getSize() {
        return size;
    }

    public Resource setSize(Long size) {
        this.size = size;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Resource setUrl(String url) {
        this.url = url;
        return this;
    }

    public Timestamp getDbUpdateTime() {
        return dbUpdateTime;
    }

    public Resource setDbUpdateTime(Timestamp dbUpdateTime) {
        this.dbUpdateTime = dbUpdateTime;
        return this;
    }

    public Timestamp getDbCreateTime() {
        return dbCreateTime;
    }

    public Resource setDbCreateTime(Timestamp dbCreateTime) {
        this.dbCreateTime = dbCreateTime;
        return this;
    }
}