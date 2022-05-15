package ru.yellowblacksnek.basic;

import static ru.yellowblacksnek.Utils.round;

public class Ln implements MathFunction{
    protected double EPS;
    private final int MAX_ITERATIONS = 100000;
    public Ln(double eps) {
        if(eps >= 1 || eps < 1.0e-8f) throw new IllegalArgumentException("Неподдерживаемая точность");
        this.EPS = eps;
    }

    @Override
    public double apply(double x) {
        if(x == Double.POSITIVE_INFINITY) return Double.POSITIVE_INFINITY;
        if(!Double.isFinite(x) || x < 0) return Double.NaN;
        if(x == 0.0) return Double.NEGATIVE_INFINITY;


        double sum = 0;
        int n = 1;
        while(true){
            double current = 2;
            double xx = (x-1)/(x+1);
            double nn = 2*n-1;
            current *= (Math.pow(xx, nn) / nn);
            sum += current;
            n++;
            if(!Double.isFinite(sum)) break;
            if(Math.abs(current) < EPS) break;
            if(n >= MAX_ITERATIONS) break;
        }
        return round(sum, EPS);
    }

//    @Override
//    public double apply(double... args) {
//        return apply(args[0]);
//    }
}
