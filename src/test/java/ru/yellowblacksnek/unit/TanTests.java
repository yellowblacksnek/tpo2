package ru.yellowblacksnek.unit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.yellowblacksnek.functions.*;
import ru.yellowblacksnek.Utils;
import ru.yellowblacksnek.basic.Sin;

import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TanTests {
    private static final double EPSILON = 0.000001;
    private static final double DELTA = 0.001;

    private static Sin sinMock;
    private static Cos cosMock;

    public static Collection<Object[]> tanTestData() {
        return Arrays.asList(new Object[][] {
                {0.000, 0.000},{-0.000, -0.000},{0.524, 0.578},
                {-0.524, -0.578},{0.785, 0.999},{-0.785, -0.999},
                {1.047, 1.731},{-1.047, -1.731},{2.094, -1.734},{-2.094, 1.734},
                {2.356, -1.000},{-2.356, 1.000},{2.618, -0.577},
                {-2.618, 0.577},{3.142, 0.000},{-3.142, -0.000},
                {3.665, 0.577},{-3.665, -0.577},{3.927, 1.000},
                {-3.927, -1.000},{4.189, 1.733},{-4.189, -1.733}, {5.236, -1.732},
                {-5.236, 1.732},{5.498, -1.000},{-5.498, 1.000},
                {5.760, -0.577},{-5.760, 0.577},
                {Double.NaN, Double.NaN},{Double.POSITIVE_INFINITY, Double.NaN},{Double.NEGATIVE_INFINITY, Double.NaN},

                {PI/2, Double.NaN}, {-PI/2, Double.NaN},
                {3*PI/2, Double.NaN},{-3*PI/2, Double.NaN},
        });
    }

    @BeforeAll
    public static void init() {
        sinMock = mock(Sin.class);
        cosMock = mock(Cos.class);

        when(sinMock.apply(anyDouble())).thenAnswer(i -> Utils.round(Math.sin(i.getArgument(0)), EPSILON));
        when(cosMock.apply(anyDouble())).thenAnswer(i -> Utils.round(Math.cos(i.getArgument(0)), EPSILON));
    }

    @DisplayName("test tan")
    @ParameterizedTest(name = "tan({0})={1}")
    @MethodSource("tanTestData")
    public void testValues(double x, double y) {
        Tan tan = new Tan(cosMock, sinMock);
//        System.out.print(String.format(Locale.ROOT, "{%.3f, %.3f},", x, Math.tan(x)));
        assertEquals(y, tan.apply(x), DELTA);
    }
}
