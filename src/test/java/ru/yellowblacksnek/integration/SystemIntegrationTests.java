package ru.yellowblacksnek.integration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.yellowblacksnek.functions.*;
import ru.yellowblacksnek.MathSystem;

import java.util.Arrays;
import java.util.Collection;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

public class SystemIntegrationTests {
    private final double EPSILON = 0.0000001;
    private final double DELTA = 0.001;

    public static Collection<Object[]> systemTestData() {
        return Arrays.asList(new Object[][] {
                {-1.047, -1.731}, {-PI/2, Double.NaN},

                {0.3, 2.908}, {0.4, 2.532}, {1, Double.NaN}, {3, 0.448}, {5, 0.074}, {5.596, 0}, {10, -0.334}, {15, -0.521}, {100, -0.872}, {1000, -0.148}, {10000, 1.837},
                {Double.NaN, Double.NaN},{Double.POSITIVE_INFINITY, Double.NaN},{Double.NEGATIVE_INFINITY, Double.NaN},

        });
    }

    @DisplayName("test system")
    @ParameterizedTest(name = "sys({0}) = {1}")
    @MethodSource("systemTestData")
    void testValues(double x, double y){
        Tan tan = spy(new Tan(EPSILON));
        Log log2 = spy(new Log(EPSILON, 2));
        Log log3 = spy(new Log(EPSILON, 3));
        Log log5 = spy(new Log(EPSILON, 5));
        Log log10 = spy(new Log(EPSILON, 10));

        MathSystem sys = new MathSystem(tan, log2, log3, log5, log10);
        assertEquals(y, sys.apply(x), DELTA);

        if(x <= 0) {
            verify(tan).apply(x);
            verifyNoInteractions(log2, log3, log5, log10);
        } else {
            verifyNoInteractions(tan);
            verify(log2).apply(x);
            verify(log3).apply(x);
            verify(log5).apply(x);
            verify(log10).apply(x);
        }
    }
}
