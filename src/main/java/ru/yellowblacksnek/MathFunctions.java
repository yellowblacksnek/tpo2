package ru.yellowblacksnek;

import ru.yellowblacksnek.basic.Cos;
import ru.yellowblacksnek.basic.Ln;
import ru.yellowblacksnek.basic.MathFunction;

public class MathFunctions {
//    public static double sin(double x) {
//        return new Cos(0.0001).apply(Math.PI/2-x);
//    }

    public static class Sin extends MathFunction {
        Cos cos;
        public Sin(double eps) {super(eps); cos = new Cos(eps);}
        @Override public double apply(double... args) {return apply(args[0]);}
        public double apply(double x) {
            return cos.apply(Math.PI/2-x);
        }
    }

    public static class Tan extends MathFunction {
        Sin sin;
        Cos cos;
        public Tan(double eps) {super(eps); cos = new Cos(eps); sin = new Sin(eps);}
        @Override public double apply(double... args) {return apply(args[0]);}
        public double apply(double x) {
            double cosRes = cos.apply(x);
            if(cosRes == 0.0) return Double.NaN;
            return sin.apply(x)/cosRes;
        }
    }

//    public static double tan(double x) {
//        double cos = CosExpansion.cos(x);
//        if(Math.abs(cos) < CosExpansion.MIN_EPS) return Double.NaN;
//        double sin = sin(x);
//        return sin/cos;
//    }

    public static class Log extends MathFunction {
        Ln ln;
        public Log(double eps) {super(eps); ln = new Ln(eps);}
        @Override public double apply(double... args) {return apply(args[0], args[1]);}
        public double apply(double a, double x) {
            double lnBase = ln.apply(a);
            if(lnBase == 0.0) return Double.NaN;
            return ln.apply(x) / lnBase;
        }
    }
//
//    public static double log(double a, double x) {
//        return LnExpansion.ln(x) / LnExpansion.ln(a);
//    }
}
