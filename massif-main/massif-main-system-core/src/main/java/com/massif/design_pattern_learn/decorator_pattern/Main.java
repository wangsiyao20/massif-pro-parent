package com.massif.design_pattern_learn.decorator_pattern;

import com.massif.design_pattern_learn.decorator_pattern.model.Bacon;
import com.massif.design_pattern_learn.decorator_pattern.model.Egg;
import com.massif.design_pattern_learn.decorator_pattern.model.FiredNoodles;

public class Main {

    public static void main(String[] args) {
        FiredNoodles firedNoodles = new FiredNoodles();
        new Egg(firedNoodles);
        new Bacon(firedNoodles);
        System.out.println(firedNoodles.cost());
    }
}
