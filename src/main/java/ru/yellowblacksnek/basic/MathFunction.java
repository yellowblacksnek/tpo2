package ru.yellowblacksnek.basic;

public abstract class MathFunction {
    protected int MAX_ITERATIONS;
    protected double EPS;

    public MathFunction(int iterations, double epsilon) {
        MAX_ITERATIONS = iterations;
        EPS = epsilon;
    }

    public MathFunction(double epsilon) {
        MAX_ITERATIONS = 0;
        EPS = epsilon;
    }

    public abstract double apply(double ...args);
}
