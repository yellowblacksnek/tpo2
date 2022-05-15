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
import java.util.Locale;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LnTests {
    private final double EPSILON = 0.000001;
    private final double DELTA = 0.001;

    public static Collection<Object[]> lnTestData() {
        return Arrays.asList(new Object[][] {
                {-1.000, Double.NaN}, {0.000, Double.NEGATIVE_INFINITY},{0.524, -0.646},
                {0.785, -0.242},{1.047, 0.046},{1.571, 0.452},
                {2.094, 0.739}, {2.356, 0.857},{2.618, 0.962},
                {3.142, 1.145}, {3.665, 1.299},{3.927, 1.368},
                {4.189, 1.432}, {4.712, 1.550},{5.236, 1.656},
                {5.498, 1.704}, {5.760, 1.751}, {Math.E, 1},
                {Double.NaN, Double.NaN},{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},{Double.NEGATIVE_INFINITY, Double.NaN},

        });
    }
    @Test
    void testIllegalEpsilon() {
        assertThrows(IllegalArgumentException.class, () -> new Ln(1.0));
        assertThrows(IllegalArgumentException.class, () -> new Ln(2.0));
        assertThrows(IllegalArgumentException.class, () -> new Ln(1.0e-10f));
    }

    @DisplayName("test ln")
    @ParameterizedTest(name = "ln({0}) = {1}")
    @MethodSource("lnTestData")
    void testValues(double x, double y){
        Ln ln = new Ln(EPSILON);
//        System.out.print(String.format(Locale.ROOT, "{%.3f, %.3f},", x, Math.log(x)));
        assertEquals(y, ln.apply(x), DELTA);
    }
}
