package com.massif.design_pattern_learn.decorator_pattern.model;

public abstract class Garnish {

    private AbstractFastFood fastFood;

    public Garnish(AbstractFastFood fastFood, float price, String describe) {
        this.fastFood = fastFood;
        fastFood.setPrice(fastFood.getPrice() + price);
        fastFood.setDescribe(fastFood.getDescribe() + "+" + describe);
    }

    public AbstractFastFood getFastFood() {
        return fastFood;
    }

    public void setFastFood(AbstractFastFood fastFood) {
        this.fastFood = fastFood;
    }
}
