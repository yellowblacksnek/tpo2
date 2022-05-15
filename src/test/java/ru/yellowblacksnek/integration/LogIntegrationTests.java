package ru.yellowblacksnek.integration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.yellowblacksnek.functions.*;
import ru.yellowblacksnek.basic.Ln;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

public class LogIntegrationTests {
    private final double EPSILON = 0.000001;
    private final double DELTA = 0.001;

    public static Collection<Object[]> logTestData() {
        return Arrays.asList(new Object[][] {
                {-1, 2, Double.NaN}, {0, 10, Double.NaN}, {1, 5, Double.NaN},
                {0.5, -1, Double.NaN}, {0.5, 0, Double.NaN}, {Double.NaN, 10, Double.NaN},
                {Double.NEGATIVE_INFINITY, 10, Double.NaN}, {Double.POSITIVE_INFINITY, 10, 0},
                {0.5, Double.NaN, Double.NaN}, {0.5, Double.NEGATIVE_INFINITY, Double.NaN}, {0.5, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY},
                {0.5, 1, 0}, {2, 2, 1}, {2, 3, 1.585}, {2, 8, 3}, {3, 10, 2.096}, {3, 15, 2.465}, {3, 27, 3}, {4, 5, 1.161}, {4, 16, 2}, {10, 1000, 3}, {Math.E, Math.E*Math.E, 2}
        });
    }

    @DisplayName("integration test log")
    @ParameterizedTest(name = "log_{0}({1}) = {2}")
    @MethodSource("logTestData")
    void testValues(double base, double x, double y) {
        Ln lnSpy = spy(new Ln(EPSILON));
        Log log = new Log(base, lnSpy);
        assertEquals(y, log.apply(x), DELTA);
        if(base > 0 && base != 1 && x > 0 && Math.log(base)!=0) {
            if(x == base) verify(lnSpy, times(2)).apply(x);
            else {
                verify(lnSpy).apply(x);
                verify(lnSpy).apply(base);
            }
        } else {
            verifyNoInteractions(lnSpy);
        }
    }
}
