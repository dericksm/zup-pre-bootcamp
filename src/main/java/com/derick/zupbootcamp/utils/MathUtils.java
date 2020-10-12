package com.derick.zupbootcamp.utils;

import java.util.Random;

public class MathUtils {

    public static Integer generateRandom(int length) {
        int min = (int) Math.pow(10, length - 1);
        int max = (int) Math.pow(10, length); // bound is exclusive

        Random random = new Random();

        return Integer.valueOf(random.nextInt(max - min) + min);
    }


}
