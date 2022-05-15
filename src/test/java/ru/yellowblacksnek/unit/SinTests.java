package ru.yellowblacksnek.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import ru.yellowblacksnek.basic.Ln;
import ru.yellowblacksnek.basic.Sin;

import java.util.Arrays;
import java.util.Collection;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SinTests {
    private final double EPSILON = 0.000001;
    private final double DELTA = 0.001;

    public static Collection<Object[]> sinTestData() {
        return Arrays.asList(new Object[][] {
                {0.000, 0.000},{-0.000, -0.000},{0.524, 0.500},
                {-0.524, -0.500},{0.785, 0.707},{-0.785, -0.707},
                {1.047, 0.866},{-1.047, -0.866},{1.571, 1.000},{-1.571, -1.000},
                {2.094, 0.866},{-2.094, -0.866},{2.356, 0.707},{-2.356, -0.707},
                {2.618, 0.500},{-2.618, -0.500},{3.142, 0.000},{-3.142, -0.000},
                {3.665, -0.500},{-3.665, 0.500},{3.927, -0.707},{-3.927, 0.707},
                {4.189, -0.866},{-4.189, 0.866},{4.712, -1.000},{-4.712, 1.000},
                {5.236, -0.866},{-5.236, 0.866},{5.498, -0.707},{-5.498, 0.707},
                {5.760, -0.500},{-5.760, 0.500},
                {Double.NaN, Double.NaN}, {Double.POSITIVE_INFINITY, Double.NaN}, {Double.NEGATIVE_INFINITY, Double.NaN},
        });
    }

    @Test
    void testIllegalEpsilon() {
        assertThrows(IllegalArgumentException.class, () -> new Ln(1.0));
        assertThrows(IllegalArgumentException.class, () -> new Ln(2.0));
        assertThrows(IllegalArgumentException.class, () -> new Ln(1.0e-10f));
    }

    @DisplayName("test sin")
    @ParameterizedTest(name = "sin({0}) = {1}")
    @MethodSource("sinTestData")
    void testValues(double x, double y){
        Sin sin = new Sin(EPSILON);
        assertEquals(y, sin.apply(x), DELTA);
    }
}
