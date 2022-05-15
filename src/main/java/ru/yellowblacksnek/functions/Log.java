package ru.yellowblacksnek.functions;

import ru.yellowblacksnek.basic.Ln;
import ru.yellowblacksnek.basic.MathFunction;

public class Log implements MathFunction {
    Ln ln;
    double base;
    public Log(double eps, double base) {this(base, new Ln(eps));}
    public Log(double base, Ln ln) {this.base = base; this.ln = ln;}

    //        @Override public double apply(double... args) {return apply(args[0]);}
    @Override public double apply(double x) {
        if(Double.isNaN(base) || base <= 0 || Math.abs(base-1) < 1.0e-15f ||
                x <= 0 || Double.isNaN(x) ) return Double.NaN;
        double lnBase = ln.apply(base);
        if(lnBase == 0.0) return Double.NaN;
        return ln.apply(x) / lnBase;
    }
}
