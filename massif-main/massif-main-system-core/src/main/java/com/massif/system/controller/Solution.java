package com.massif.system.controller;

import java.util.Map;
import java.util.TreeMap;

/**
 * 一个算法题  未完成
 */

class Solution {

    private Map<Integer, String> luoMaNum = new TreeMap<>((o1, o2) -> {
        return -o1.compareTo(o2);
    });

    Solution() {
        luoMaNum.put(1, "I");
        luoMaNum.put(5, "V");
        luoMaNum.put(10, "X");
        luoMaNum.put(50, "L");
        luoMaNum.put(100, "C");
        luoMaNum.put(500, "D");
        luoMaNum.put(1000, "M");
    }

    public String intToRoman(int num) {
        luoMaNum.forEach((k, v) -> {

        });
        return null;
    }
}
