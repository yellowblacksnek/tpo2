package ru.yellowblacksnek.basic;

public class Ln extends MathFunction{
    public Ln(double eps) {
        super(100000, eps);
    }

    public double apply(double x) {
        if(!Double.isFinite(x)) return Double.NaN;

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
        return sum;
    }

    @Override
    public double apply(double... args) {
        return apply(args[0]);
    }
}
