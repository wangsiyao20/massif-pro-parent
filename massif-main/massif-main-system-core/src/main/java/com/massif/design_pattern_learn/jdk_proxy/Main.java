package com.massif.design_pattern_learn.jdk_proxy;

import com.massif.design_pattern_learn.jdk_proxy.model.ISellHouse;
import com.massif.design_pattern_learn.jdk_proxy.model.Impl.HouseProxyFactory;

public class Main {

    public static void main(String[] args) {
        HouseProxyFactory houseProxy = new HouseProxyFactory();
        ISellHouse proxy = houseProxy.getProxy();
        proxy.sellHouse();
    }

}
