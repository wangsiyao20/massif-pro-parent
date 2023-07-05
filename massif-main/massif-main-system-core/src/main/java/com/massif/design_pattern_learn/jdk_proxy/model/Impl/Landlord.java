package com.massif.design_pattern_learn.jdk_proxy.model.Impl;

import com.massif.design_pattern_learn.jdk_proxy.model.ISellHouse;

public class Landlord implements ISellHouse {

    @Override
    public void sellHouse() {
        System.out.println("我是房东，我要卖房子。");
    }
}
