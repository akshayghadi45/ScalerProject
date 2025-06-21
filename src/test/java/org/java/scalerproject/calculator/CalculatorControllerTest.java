package org.java.scalerproject.calculator;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class CalculatorControllerTest {

    CalculatorService calculatorService = Mockito.mock(CalculatorService.class);
    CalculatorController controller = new CalculatorController(calculatorService);

    @Test
    public void testCalculatorControllerAddAcceptsTwoIntegerReturnSum(){
        //Mocking


        when(calculatorService.add(5,10)).thenReturn(15);
        //or
        when(calculatorService.add(anyInt(),anyInt())).thenReturn(100);
        //AAA method

        //when

        //Arrange
        int a = 5;
        int b = 10;
        int expectedSum = 15;

        //Act
        int actualresult = controller.add(a,b);

        //Assert
        assertEquals(expectedSum, actualresult);
    }
}