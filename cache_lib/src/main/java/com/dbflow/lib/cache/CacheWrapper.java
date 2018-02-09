package com.dbflow.lib.cache;

import com.dbflow.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * <p>Description:
 * <p>Company:
 * <p>Email:bjxm2013@163.com
 * <p>@author:Created by Devin Sun on 2018/2/5.
 */

@Table(database = AppDatabase.class, allFields = true)
class CacheWrapper extends BaseModel {

    @PrimaryKey
    private String key;
    private String dataJson;
    private long effective;
    private long updateTime;


    public CacheWrapper() {
    }

    public CacheWrapper(String key) {
        this.key = key;
    }

    public CacheWrapper(String key, String dataJson) {
        this.key = key;
        this.dataJson = dataJson;
    }

    public CacheWrapper(String key, String dataJson, long effective) {
        this.key = key;
        this.dataJson = dataJson;
        this.effective = effective;
    }

    public CacheWrapper(String key, String dataJson, long effective, long updateTime) {
        this.key = key;
        this.dataJson = dataJson;
        this.effective = effective;
        this.updateTime = updateTime;
    }



    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDataJson() {
        return dataJson;
    }

    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }

    public long getEffective() {
        return effective;
    }

    public void setEffective(long effective) {
        this.effective = effective;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CacheWrapper{" +
                "key='" + key + '\'' +
                ", dataJson='" + dataJson + '\'' +
                ", effective=" + effective +
                ", updateTime=" + updateTime +
                '}';
    }
}
