package ru.yellowblacksnek.stubs;
public class MathSetStub {
    public static double calc(double x) {
//        if(Math.abs(x-1) < LnExpansion.MIN_EPS) throw new IllegalArgumentException("X cannot be 1");
        if(x <= 0) return Math.tan(x);
        else {
            double A,B,C,D,E,F,G;

            A = Math.log(x)/Math.log(3);
            B = Math.log(x)/Math.log(2);
            C = Math.log10(x);
            D = Math.log(x)/Math.log(5);
            E = Math.log10(x);
            F = Math.log(x)/Math.log(3);
            G = Math.log(x)/Math.log(5);

            // (A/B)*C*С-D-E+(F/G)
            //log(3,x)/log(2,x) * log(10, x*x) - log(5,x) - log(10,x) + log(3,x)/log(5,x);
            return A/B*C*C-D-E+F/G;
        }
    }
}
