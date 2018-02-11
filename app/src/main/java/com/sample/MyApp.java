package com.sample;

import android.app.Application;

import com.dbflow.lib.DBManager;
import com.devin.UtilManager;

/**
 * <p>Description:
 * <p>Company:
 * <p>Email:bjxm2013@163.com
 * <p>@author:Created by Devin Sun on 2018/2/5.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UtilManager.init(this);
        DBManager.init(this,"TestDB",-1);
    }
}
