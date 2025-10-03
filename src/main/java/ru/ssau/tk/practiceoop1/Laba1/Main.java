package ru.ssau.tk.practiceoop1.Laba1;

import ru.ssau.tk.practiceoop1.Laba1.Interfaces.Function1D;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Function1D f = x -> (x - 2) * (x - 5);

        double a = 0;
        double b = 5;
        double epsilon = 0.00001;
        int maxIterations = 51;

        Result result = Fibonaci.findExtremum(f, a, b, epsilon, maxIterations);

        System.out.println(result);
    }
}
