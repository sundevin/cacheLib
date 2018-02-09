package com.dbflow.lib;

import android.content.Context;

import com.raizlabs.android.dbflow.config.DatabaseConfig;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * <p>Description:
 * <p>Company:
 * <p>Email:bjxm2013@163.com
 * <p>@author:Created by Devin Sun on 2018/2/6.
 */

public class DBManager {

    //dbflow,Ormlite,realm android,greendao,xutils3,room

    private static long cacheEffective;

    /**
     * 初始化数据库
     *
     * @param context
     * @param dbName             数据库名称
     * @param effective 缓存数据的有效期，在存储单条数据时也可单独设置  单位：毫秒，-1表示永久有效
     */
    public static void init(Context context, String dbName, long effective) {
        DBManager.cacheEffective = effective;
        FlowManager.init(FlowConfig.builder(context.getApplicationContext())
                .addDatabaseConfig(DatabaseConfig.builder(AppDatabase.class)
                        .databaseName(dbName)
                        .build())
                .build());
    }

    public static long getCacheEffective() {
        return cacheEffective;
    }
}
