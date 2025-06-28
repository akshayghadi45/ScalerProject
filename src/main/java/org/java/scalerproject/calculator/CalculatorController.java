package org.java.scalerproject.calculator;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

@Getter
@Setter
@Controller
public class CalculatorController {

    private final CalculatorService calculatorService;
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }
    public  int add(int a, int b) {
        System.out.println("Controller logic before adding");
        int sum =calculatorService.add(a,b);
        System.out.println("Controller logic after adding");
        return sum;
    }
}
