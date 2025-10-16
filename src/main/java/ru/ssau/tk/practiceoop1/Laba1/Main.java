package ru.ssau.tk.practiceoop1.Laba1;

import ru.ssau.tk.practiceoop1.Laba1.Interfaces.Function1D;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Function1D f = x -> (x - 2.0) * (x - 5.0);

        double a = 0.0;
        double b = 5.0;
        double epsilon = 1e-6;
        int maxIterations = 1000;

        Result result1 = Dichotomy.findExtrema(f, a, b, epsilon, maxIterations);
        Result result2 = GoldenRatio.findExtrema(f, a, b, epsilon*(Math.sqrt(5)-1)/2, maxIterations);
        Result result3 = Fibonacci.findExtrema(f, a, b, epsilon, maxIterations);

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }
}
