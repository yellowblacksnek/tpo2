package ru.yellowblacksnek;

import static ru.yellowblacksnek.MathFunctions.*;

public class MathSystem {
    Tan tan;
    Log log;
    public MathSystem(double eps) {
        tan = new Tan(eps);
        log = new Log(eps);
    }
    public double apply(double x) {
//        if(Math.abs(x-1) < LnExpansion.MIN_EPS) throw new IllegalArgumentException("X cannot be 1");
        if(x <= 0) return tan.apply(x);
        else {
            double A,B,C,D,E,F,G;

            A = log.apply(3,x);
            B = log.apply(2,x);
            C = log.apply(10, x);
            D = log.apply(5,x);
            E = C; //log(10, x);
            F = A; //log(3,x);
            G = D; //log(5,x);

            // (A/B)*C*С-D-E+(F/G)
            //log(3,x)/log(2,x) * log(10, x*x) - log(5,x) - log(10,x) + log(3,x)/log(5,x);
            return A/B*C*C-D-E+F/G;
        }
    }
}
