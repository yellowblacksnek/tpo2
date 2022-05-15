package ru.yellowblacksnek.functions;

import ru.yellowblacksnek.basic.MathFunction;
import ru.yellowblacksnek.basic.Sin;

public class Cos implements MathFunction {
    Sin sin;

    public Cos(double eps) {
        this(new Sin(eps));
    }

    public Cos(Sin sin) {
        this.sin = sin;
    }

    //        @Override public double apply(double... args) {return apply(args[0]);}
    @Override
    public double apply(double x) {
        return sin.apply(Math.PI / 2 - x);
    }
}
