package ru.yellowblacksnek;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.yellowblacksnek.MathFunctions.*;

import org.mockito.Mock;
import ru.yellowblacksnek.basic.*;
import ru.yellowblacksnek.stubs.MathSetStub;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTests {

    double precision = 0.000000001;
    double delta = 0.0001;

    @Test
    public void testCos() {
        double x = Math.PI/2;
        assertEquals(Math.cos(x), new Cos(precision).apply(x), delta);
    }

    @Test
    public void testSin() {
        Sin sin = new Sin(precision);
        sin.cos = mock(Cos.class);
        when(sin.cos.apply(0)).thenReturn(1d);
        assertEquals(Math.sin(Math.PI/2), sin.apply(Math.PI/2), delta);
    }

    @Test
    public void testTan() {
        double x = Math.PI/2;
        assertEquals(Math.tan(x), new Tan(precision).apply(x), delta);
    }

    @Test
    public void testLn() {
        double x = 3;
        assertEquals(Math.log(x), new Ln(precision).apply(x), delta);
    }

    @Test
    public void testLog() {
        double x = 1;
        assertEquals(Math.log(x)/Math.log(1), new Log(precision).apply(1, x), delta);
    }

    @Test
    public void testSystem() {
        double x = 1272;
        assertEquals(MathSetStub.calc(x), new MathSystem(precision).apply(x), delta);
    }

    @Test
    public void testCSV(){
        Cos cos = new Cos(0.0001);
        ModulesCSVRunner.runMathFunction(cos, -Math.PI, Math.PI, 0.01);
    }
}
