package com.massif.design_pattern_learn.decorator_pattern.model;

public class FiredNoodles extends AbstractFastFood {


    public FiredNoodles() {
        super.setPrice(15);
        super.setDescribe("炒面");
    }

    @Override
    public String cost() {
        return getDescribe() + "      :     " + getPrice() + "元";
    }
}
