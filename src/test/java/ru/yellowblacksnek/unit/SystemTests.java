package ru.yellowblacksnek.unit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.yellowblacksnek.functions.*;
import ru.yellowblacksnek.MathSystem;

import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SystemTests {
    private final double DELTA = 0.001;

    private static Tan tan;
    private static Log log2, log3, log5, log10;

    public static Collection<Object[]> systemTestData() {
        return Arrays.asList(new Object[][] {
                {-1.047, -1.731}, {-PI/2, Double.NaN}, {0, 0},

                {0.3, 2.908}, {0.4, 2.532}, {1, Double.NaN}, {3, 0.448}, {5, 0.074}, {5.596, 0}, {10, -0.334}, {15, -0.521}, {100, -0.872}, {1000, -0.148}, {10000, 1.837},
                {Double.NaN, Double.NaN},{Double.POSITIVE_INFINITY, Double.NaN},{Double.NEGATIVE_INFINITY, Double.NaN},

        });
    }

    private static Log createLogMock(double base) {
        Log logToMock = mock(Log.class);
        when(logToMock.apply(anyDouble())).thenAnswer(i -> Math.log(i.getArgument(0))/Math.log(base));
        return logToMock;
    }

    @BeforeAll
    public static void init() {
        tan = mock(Tan.class);
        when(tan.apply(anyDouble())).thenAnswer(i -> Math.tan(i.getArgument(0)));
        when(tan.apply(Mockito.doubleThat(arg -> (Math.abs(Math.cos(arg)) < 1.0e-15f)))).thenReturn(Double.NaN);

        log2 = createLogMock(2);
        log3 = createLogMock(3);
        log5 = createLogMock(5);
        log10 = createLogMock(10);
    }

    @DisplayName("test system")
    @ParameterizedTest(name = "sys({0}) = {1}")
    @MethodSource("systemTestData")
    void testValues(double x, double y){
        MathSystem sys = new MathSystem(tan, log2, log3, log5, log10);
        assertEquals(y, sys.apply(x), DELTA);
    }
}
