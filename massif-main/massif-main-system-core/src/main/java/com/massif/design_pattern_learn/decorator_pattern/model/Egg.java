package com.massif.design_pattern_learn.decorator_pattern.model;

public class Egg extends Garnish {

    // 父类没有定义了有参构造就没了默认构造，所以这里必须重写一个构造方法，按照父类的构造来个super(xxx);

    public Egg(AbstractFastFood fastFood) {
        super(fastFood, 1, "鸡蛋");
    }

}
