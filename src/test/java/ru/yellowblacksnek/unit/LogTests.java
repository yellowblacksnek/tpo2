package ru.yellowblacksnek.unit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import ru.yellowblacksnek.functions.*;
import ru.yellowblacksnek.basic.Ln;
import ru.yellowblacksnek.basic.Sin;

import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogTests {
    private final double EPSILON = 0.000001;
    private final double DELTA = 0.001;

    private static Ln lnMock;

    public static Collection<Object[]> logTestData() {
        return Arrays.asList(new Object[][] {
                {-1, 2, Double.NaN}, {0, 10, Double.NaN}, {1, 5, Double.NaN},
                {0.5, -1, Double.NaN}, {0.5, 0, Double.NaN}, {Double.NaN, 10, Double.NaN},
                {Double.NEGATIVE_INFINITY, 10, Double.NaN}, {Double.POSITIVE_INFINITY, 10, 0},
                {0.5, Double.NaN, Double.NaN}, {0.5, Double.NEGATIVE_INFINITY, Double.NaN}, {0.5, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY},
                {0.5, 1, 0}, {2, 2, 1}, {2, 3, 1.585}, {2, 8, 3}, {3, 10, 2.096}, {3, 15, 2.465}, {3, 27, 3}, {4, 5, 1.161}, {4, 16, 2}, {10, 1000, 3}, {Math.E, Math.E*Math.E, 2}
        });
    }

    @BeforeAll
    public static void init() {
        lnMock = mock(Ln.class);
        when(lnMock.apply(anyDouble())).thenAnswer(i -> Math.log(i.getArgument(0)));
    }

    @DisplayName("test log")
    @ParameterizedTest(name = "log_{0}({1}) = {2}")
    @MethodSource("logTestData")
    void testValues(double base, double x, double y) {
        Log log = new Log(base, lnMock);
        assertEquals(y, log.apply(x), DELTA);
    }
}
