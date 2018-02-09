package com.dbflow.lib.cache;

import com.dbflow.lib.DBManager;
import com.google.gson.Gson;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * <p>Description:
 * <p>Company:
 * <p>Email:bjxm2013@163.com
 * <p>@author:Created by Devin Sun on 2018/2/5.
 */

public class CacheManager {

    private static Gson gson = new Gson();

    /**
     * 永久有效
     */
    private static final long PERMANENT_VALIDITY = -1L;

    private static long cacheEffective = DBManager.getCacheEffective();

    private static <T> String toStr(T t) {
        return gson.toJson(t);
    }


    /**
     * 查询数据
     *
     * @param primaryKey
     * @return
     */
    private static CacheWrapper select(String primaryKey) {

        return SQLite.select().from(CacheWrapper.class)
                .where(CacheWrapper_Table.key.eq(primaryKey))
                .querySingle();
    }


    /**
     * 保存一个对象
     *
     * @param primaryKey 主键
     * @param t          需要保存到数据库的对象
     * @param <T>
     * @return
     */
    public static <T> boolean save(String primaryKey, T t) {

        if (exists(primaryKey)) {
            CacheWrapper cacheWrapper = select(primaryKey);
            return save(primaryKey, t, cacheWrapper.getEffective());
        } else {
            return save(primaryKey, t, cacheEffective);
        }
    }


    /**
     * 保存对象到数据库
     *
     * @param primaryKey 主键
     * @param t          需要保存到数据库的对象
     * @param <T>
     * @return
     */
    public static <T> boolean save(String primaryKey, T t, long cacheEffective) {
        CacheWrapper tdbWrapBean = new CacheWrapper(primaryKey, toStr(t), cacheEffective, System.currentTimeMillis());
        return tdbWrapBean.save();
    }


    /**
     * 删除数据
     *
     * @param primaryKey 主键
     * @return
     */
    public static boolean delete(String primaryKey) {
        CacheWrapper tdbWrapBean = new CacheWrapper(primaryKey);
        return tdbWrapBean.delete();
    }


    /**
     * 数据是否存在(记录在，但数据不一定是有效的)
     *
     * @param primaryKey
     * @return
     */
    public static boolean exists(String primaryKey) {
        CacheWrapper tdbWrapBean = new CacheWrapper(primaryKey);
        return tdbWrapBean.exists();
    }


    /**
     * 有效数据是否存在
     *
     * @param primaryKey
     * @return
     */
    public static boolean existsValid(String primaryKey) {
        CacheWrapper dbWrapBean = SQLite.select().from(CacheWrapper.class)
                .where(CacheWrapper_Table.key.eq(primaryKey), CacheWrapper_Table.dataJson.isNotNull())
                .querySingle();

        if (dbWrapBean == null) {
            return false;
        }

        if (dbWrapBean.getEffective() + dbWrapBean.getUpdateTime() < System.currentTimeMillis() && dbWrapBean.getEffective() != PERMANENT_VALIDITY) {
            return false;
        }

        return true;
    }


    /**
     * 查询一条有效的数据
     *
     * @param primaryKey
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T selectValid(String primaryKey, Class<T> cls) {

        CacheWrapper dbWrapBean = SQLite.select().from(CacheWrapper.class)
                .where(CacheWrapper_Table.key.eq(primaryKey), CacheWrapper_Table.dataJson.isNotNull())
                .querySingle();

        if (dbWrapBean == null) {
            return null;
        }

        if (dbWrapBean.getEffective() + dbWrapBean.getUpdateTime() < System.currentTimeMillis() && dbWrapBean.getEffective() != PERMANENT_VALIDITY) {
            return null;
        }
        try {
            return gson.fromJson(dbWrapBean.getDataJson(), cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除所有无效数据
     */
    public static void deleteAllInvalid() {
        List<CacheWrapper> cacheWrapperList = SQLite.select().from(CacheWrapper.class)
                .where(CacheWrapper_Table.effective.isNot(PERMANENT_VALIDITY))
                .queryList();
        for (CacheWrapper cacheWrapper : cacheWrapperList) {
            if (cacheWrapper.getEffective() + cacheWrapper.getUpdateTime() < System.currentTimeMillis()) {
                delete(cacheWrapper.getKey());
            }
        }
    }

}
