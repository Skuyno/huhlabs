package ru.ssau.tk.practiceoop1.Laba2;

import org.joml.Vector2d;
import ru.ssau.tk.practiceoop1.Laba2.Interfaces.FunctionND;

public class MDFibonacci {
    public static ResultND findExtrema(FunctionND f, Vector2d a, Vector2d b, double eps, int maxIterations) {

        int evaluations = 0;
        int iterations = 0;

        double condition = (b.distance(a)) / eps;
        double fib_t = 0.0, fib_1 = 1.0, fib_2 = 1.0;

        while (fib_2 < condition)
        {
            iterations++;
            fib_t = fib_1;
            fib_1 = fib_2;
            fib_2 += fib_t;
        }

    }
}
