package ru.yellowblacksnek.basic;

import static ru.yellowblacksnek.Utils.round;

public class Sin implements MathFunction{
    protected double EPS;
    private final int MAX_ITERATIONS = 50;
    public Sin(double eps) {
        if(eps >= 1 || eps < 1.0e-8f) throw new IllegalArgumentException("Неподдерживаемая точность");
        this.EPS = eps;
    }

    @Override
    public double apply(double x) {
        if(!Double.isFinite(x)) return Double.NaN;
        x = x % (2*Math.PI);

        double sum = 0;
        int n = 0;
        while(true){
            double current = n % 2 == 0 ? 1 : -1;
            current *= Math.pow(x, 2*n+1);
            for (int factor = 2; factor <= 2*n+1; factor++) {
                current /= factor;
            }
            sum += current;
            n++;
            if(!Double.isFinite(sum)) break;
            if(Math.abs(current) < EPS && n > 1) break;
            if(n >= MAX_ITERATIONS) break;
        }
        return round(sum, EPS);
    }

//    @Override
//    public double apply(double... args) {
//        return apply(args[0]);
//    }
}

