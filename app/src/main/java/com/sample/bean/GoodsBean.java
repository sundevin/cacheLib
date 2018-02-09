package com.sample.bean;

import android.graphics.Bitmap;

import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

/**
 * <p>Description:
 * <p>Company:
 * <p>Email:bjxm2013@163.com
 * <p>@author:Created by Devin Sun on 2018/2/1.
 */
public class GoodsBean extends BaseModel {

    private int id;
    private String name;
    private String desc;
    private double price;
    private List<String> tagList;
    private Category category;
    private Bitmap  bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public static class Category {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
