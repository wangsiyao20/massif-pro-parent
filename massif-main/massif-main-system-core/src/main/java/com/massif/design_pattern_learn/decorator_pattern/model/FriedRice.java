package com.massif.design_pattern_learn.decorator_pattern.model;

public class FriedRice extends AbstractFastFood {

    public FriedRice() {
        super.setPrice(10);
        super.setDescribe("炒米饭");
    }

    @Override
    public String cost() {
        return getDescribe() + "      :     " + getPrice() + "元";
    }
}
