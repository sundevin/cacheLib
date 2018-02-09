package com.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dbflow.lib.cache.CacheManager;
import com.devin.util.ToastUtils;
import com.sample.bean.GoodsBean;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    GoodsBean goodsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goodsBean = new GoodsBean();
        goodsBean.setId(1);
        goodsBean.setName("手机");
        goodsBean.setDesc("波导手机，手机中的战斗机~");
        goodsBean.setPrice(123.45);
        goodsBean.setTagList(Arrays.asList("高端", "大气", "上档次"));
        GoodsBean.Category category = new GoodsBean.Category();
        category.setId(1101);
        category.setName("3c");
        goodsBean.setCategory(category);

    }


    private String primaryKey = "http://www.baidu.com";

    public void add(View view) {
        boolean b = CacheManager.save(primaryKey, goodsBean, 60 * 1000);
        ToastUtils.show("add data--" + b);
    }


    public void delete(View view) {

        boolean b = CacheManager.delete(primaryKey);
        ToastUtils.show("delete data---" + b);
    }


    public void select(View view) {
        GoodsBean goodsBean = CacheManager.selectValid(primaryKey, GoodsBean.class);
        if (goodsBean == null) {
            ToastUtils.show("selectValid data---null");
        } else {
            ToastUtils.show("selectValid data---" + goodsBean.toString());
        }
    }


    public void exists(View view) {
        boolean exists = CacheManager.exists(primaryKey);
        ToastUtils.show("exists data---" + exists);
    }

    public void existsValid(View view) {
        boolean exists = CacheManager.existsValid(primaryKey);
        ToastUtils.show("exists valid data---" + exists);
    }
}
