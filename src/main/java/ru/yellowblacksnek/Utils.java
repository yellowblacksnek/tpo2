package ru.yellowblacksnek;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {
    public static double round(double value, int places) {
        if(!Double.isFinite(value)) return value;
        return new BigDecimal(value).setScale(places, RoundingMode.HALF_UP).doubleValue();
    }

    public static double round(double value, double epsilon) {
        if(!Double.isFinite(value)) return value;
        int places = 1;
        while(epsilon < 1) {
            places++;
            epsilon*=10;
        }
        return round(value, places);
    }
}
