package ru.yellowblacksnek.unit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.yellowblacksnek.functions.*;
import ru.yellowblacksnek.basic.Sin;

import static java.lang.Math.PI;

import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CosTests {
    private final double DELTA = 0.001;

    private static Sin sinMock;

    public static Collection<Object[]> cosTestData() {
        return Arrays.asList(new Object[][] {
//                {0, 1}, {PI/6, 0.866}, {PI/4, 0.707},
//                {PI/3, 0.5}, {PI/2, 0}, {2*PI/3, -0.5}, {3*PI/4, -0.707},
//                {5*PI/6, -0.866}, {PI, -1}, {7*PI/6, -0.866},
//                {5*PI/4, -0.707}, {4*PI/3, -0.5}, {3*PI/2, 0},
//                {5*PI/3, 0.5}, {7*PI/4, 0.707}, {11*PI/6, 0.866},

                {0.000, 1.000},{0.524, 0.866},{0.785, 0.707},
                {1.047, 0.500},{1.571, 0.000},{2.094, -0.500},
                {2.356, -0.707},{2.618, -0.866},{3.142, -1.000},
                {3.665, -0.866},{3.927, -0.707},{4.189, -0.500},
                {4.712, -0.000},{5.236, 0.500},{5.498, 0.707},
                {5.760, 0.866},
                {Double.NaN, Double.NaN}, {Double.POSITIVE_INFINITY, Double.NaN}, {Double.NEGATIVE_INFINITY, Double.NaN},
        });
    }

    @BeforeAll
    public static void init() {
        sinMock = mock(Sin.class);
        when(sinMock.apply(anyDouble())).thenAnswer(i -> Math.sin(i.getArgument(0)));
    }

    @DisplayName("test cos")
    @ParameterizedTest(name = "cos(+-{0})={1}")
    @MethodSource("cosTestData")
    public void testValues(double x, double y) {
        Cos cos = new Cos(sinMock);
        assertEquals(y, cos.apply(x), DELTA);
        assertEquals(y, cos.apply(-x), DELTA);
    }
}
