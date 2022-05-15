package ru.yellowblacksnek;

import ru.yellowblacksnek.basic.MathFunction;

import ru.yellowblacksnek.functions.*;

public class MathSystem implements MathFunction {
    Tan tan;
    Log log2, log3, log5, log10;
    public MathSystem(double eps) {
        this(
            new Tan(eps),
            new Log(eps, 2),
            new Log(eps, 3),
            new Log(eps, 5),
            new Log(eps, 10)
        );
    }
    public MathSystem(Tan tan, Log log2, Log log3, Log log5, Log log10) {
        this.tan = tan;
        this.log2 = log2;
        this.log3 = log3;
        this.log5 = log5;
        this.log10 = log10;
    }
    public double apply(double x) {
//        if(Math.abs(x-1) < LnExpansion.MIN_EPS) throw new IllegalArgumentException("X cannot be 1");
        if(x <= 0) return tan.apply(x);
        else {
            double A,B,C,D,E,F,G;

            A = log3.apply(x);
            B = log2.apply(x);
            C = log10.apply(x);
            D = log5.apply(x);
            E = C; //log(10, x);
            F = A; //log(3,x);
            G = D; //log(5,x);

            // (A/B)*C*С-D-E+(F/G)
            //log(3,x)/log(2,x) * log(10, x*x) - log(5,x) - log(10,x) + log(3,x)/log(5,x);
            return (A/B)*C*C-D-E+(F/G);
        }
    }
}
