package com.massif.design_pattern_learn.jdk_proxy.model.Impl;

import com.massif.design_pattern_learn.jdk_proxy.model.ISellHouse;

import java.lang.reflect.Proxy;

public class HouseProxyFactory {

    private final Landlord landlord = new Landlord();


    public ISellHouse getProxy() {

        return (ISellHouse) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                landlord.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("中介收取中介费。");
                    return method.invoke(landlord, args);
                }
        );


    }
}
