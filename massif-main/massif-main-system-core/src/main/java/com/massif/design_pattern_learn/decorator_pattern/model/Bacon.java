package com.massif.design_pattern_learn.decorator_pattern.model;

public class Bacon extends Garnish{

    public Bacon(AbstractFastFood fastFood) {
        super(fastFood, 2, "培根");
    }

}
