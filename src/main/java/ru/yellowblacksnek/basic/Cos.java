package ru.yellowblacksnek.basic;

public class Cos extends MathFunction{
    public Cos(double eps) {
        super(50, eps);
    }

    public double apply(double x) {
        if(!Double.isFinite(x)) return Double.NaN;
        x = x % (2*Math.PI);

        double sum = 1;
        int n = 1;
        while(true){
            double current = n % 2 == 0 ? 1 : -1;
            current *= Math.pow(x, 2*n);
            for (int factor = 2; factor <= 2*n; factor++) {
                current /= factor;
            }
            sum += current;
            n++;
            if(!Double.isFinite(sum)) break;
            if(Math.abs(current) < EPS && n > 1) break;
            if(n >= MAX_ITERATIONS) break;
        }
        return sum;
    }

    @Override
    public double apply(double... args) {
        return apply(args[0]);
    }
}

