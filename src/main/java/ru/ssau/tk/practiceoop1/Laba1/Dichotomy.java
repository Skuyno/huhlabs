package ru.ssau.tk.practiceoop1.Laba1;

import ru.ssau.tk.practiceoop1.Laba1.Interfaces.Function1D;

import static java.lang.Math.abs;

public class Dichotomy {
    public static Result findExtremum(Function1D f, double a, double b, double eps, int maxIterations){
        int iterations = 0;
        int functionCalls = 0;

        while (abs((b - a)) >= 2 * eps && iterations < maxIterations){
            double xc = (a + b) / 2;
            double xl = xc - eps / 10;
            double xr = xc + eps / 10;

            double fl = f.apply(xl);
            double fr = f.apply(xr);
            functionCalls += 2;

            if (fl > fr) {
                a = xl;  // минимум правее
            } else {
                b = xr;  // минимум левее
            }

            iterations++;
        }

        double res = (a + b) / 2;
        double precision = abs(b - a) / 2;

        return new Result(res, functionCalls, precision);
    }
}
