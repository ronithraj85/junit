package org.ronith.maven.calculator;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculatorImplTest {

    @Mock
    CalculatorImpl calculator;

    public static Collection<Integer[]> data() {
        return Arrays.asList(new Integer[][]{{-1, 2, 1}, {10, 3, 13}, {-19, 9, -10}});
    }

    @ParameterizedTest
    @MethodSource("data")
    public void addShouldReturnResult(int num1, int num2, int expResult) {
        when(calculator.add(num1, num2)).thenReturn(expResult);

        int add = calculator.add(num1, num2);

        Assertions.assertEquals(expResult, add);

    }


}