package com.massif.design_pattern_learn.decorator_pattern.model;

public abstract class AbstractFastFood {

    // 价格
    private float price;

    // 描述
    private String describe;


    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public abstract String cost();
}
