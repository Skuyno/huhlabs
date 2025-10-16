package ru.ssau.tk.practiceoop1.Laba1;

import ru.ssau.tk.practiceoop1.Laba1.Interfaces.Function1D;

import static java.lang.Math.abs;

public class Fibonacci {
    public static Result findExtrema(Function1D f, double a, double b, double eps, int maxIterations) {
        int functionCalls = 0;

        int iterations = 0;
        double condition = (b - a) / eps;
        double fib_t = 0.0, fib_1 = 1.0, fib_2 = 1.0;

        while (fib_2 < condition)
        {
            iterations++;
            fib_t = fib_1;
            fib_1 = fib_2;
            fib_2 += fib_t;
        }

        // while (fib(n+1) <= condition) n++;
        // n = min(n, maxIterations);

        double xl = a + ((fib_2 - fib_1) / fib_2) * (b - a);
        double xr = a + (fib_1 / fib_2) * (b - a);
        double fl = f.apply(xl);
        double fr = f.apply(xr);
        functionCalls += 2;

        for (int index = 0; index < iterations; index++) {
            fib_t = fib_2 - fib_1;
            fib_2 = fib_1;
            fib_1 = fib_t;
            if (fl > fr) {
                a = xl;
                xl = xr;  fl = fr;
                    xr = a + (fib_1 / fib_2) * (b - a);
                    fr = f.apply(xr);
                    functionCalls++;
            } else {
                b = xr;
                xr = xl;  fr = fl;
                    xl = a + ((fib_2 - fib_1) / fib_2) * (b - a);
                    fl = f.apply(xl);
                    functionCalls++;
            }
        }

        double res = (a + b) / 2.0;
        double precision = abs(b - a) / 2.0;

        return new Result(res, functionCalls, precision, Result.search_method_type.Fibonacci, iterations);
    }
}
