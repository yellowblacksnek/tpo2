package ru.yellowblacksnek.functions;

import ru.yellowblacksnek.basic.MathFunction;
import ru.yellowblacksnek.basic.Sin;

public class Tan implements MathFunction {
    Sin sin;
    Cos cos;
    public Tan(double eps) {this(new Cos(eps), new Sin(eps));}
    public Tan(Cos cos, Sin sin) {this.cos = cos; this.sin = sin;}

    @Override public double apply(double x) {
        double cosRes = cos.apply(x);
        if(cosRes == 0.0) return Double.NaN;
        return sin.apply(x)/cosRes;
    }
}
