package org.java.scalerproject.calculator;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public int add(int a, int b) {
        System.out.println("CalculatorService  before add");
        int sum = a + b;
        System.out.println("CalculatorService  after add");

        return sum;
    }
}
