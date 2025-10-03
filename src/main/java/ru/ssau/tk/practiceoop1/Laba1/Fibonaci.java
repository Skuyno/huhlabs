package ru.ssau.tk.practiceoop1.Laba1;

import ru.ssau.tk.practiceoop1.Laba1.Interfaces.Function1D;

import static java.lang.Math.abs;

public class Fibonaci {
    public static Result findExtremum(Function1D f, double a, double b, double eps, int maxIterations) {
        int functionCalls = 0;

        int n = 2;
        while (fib(n) <= (b - a) / eps) {
            n++;
        }
        if (n > maxIterations) n = maxIterations;

        double xl = a + (double) fib(n - 2) / fib(n) * (b - a);
        double xr = a + (double) fib(n - 1) / fib(n) * (b - a);

        double fl = f.apply(xl);
        double fr = f.apply(xr);
        functionCalls += 2;

        double delta = (b - a) / 100.0; // защита от совпадения точек

        for (int k = n; k > 2; k--) {
            if (fl > fr) {
                a = xl;
                xl = xr;
                fl = fr;

                xr = a + (double) fib(k - 1) / fib(k) * (b - a);
                if (abs(xr - xl) < delta) xr += delta;

                fr = f.apply(xr);
                functionCalls++;
            } else {
                b = xr;
                xr = xl;
                fr = fl;

                xl = a + (double) fib(k - 2) / fib(k) * (b - a);
                if (abs(xr - xl) < delta) xl -= delta;

                fl = f.apply(xl);
                functionCalls++;
            }
        }
        double res = (a + b) / 2.0;
        double precision = abs(b - a) / 2.0;

        return new Result(res, functionCalls, precision);
    }

    private static int fib(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int tmp = a + b;
            a = b;
            b = tmp;
        }
        return b;
    }
}
