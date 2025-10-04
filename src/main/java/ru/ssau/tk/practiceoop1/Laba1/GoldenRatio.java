package ru.ssau.tk.practiceoop1.Laba1;

import ru.ssau.tk.practiceoop1.Laba1.Interfaces.Function1D;

import static java.lang.Math.abs;

public class GoldenRatio {
    static double ψ = 0.6180339887;
    public static Result findExtrema(Function1D f, double a, double b, double eps, int maxIterations){
        int iterations = 0;
        int functionCalls = 0;

        double xl = b - ψ * abs(b - a); // или a + ψ*ψ * abs(b - a)
        double xr = a + ψ * abs(b - a);

        double fl = f.apply(xl);
        double fr = f.apply(xr);

        functionCalls += 2;

        while (abs((b - a)) >= 2 * eps && iterations < maxIterations){
            if (fl > fr) {
                a = xl;  // минимум правее
                xl = xr;
                fl = fr;
                xr = a + ψ * abs(b - a);
                fr = f.apply(xr);
            } else {
                b = xr;  // минимум левее
                xr = xl;
                fr = fl;
                xl = b - ψ * abs(b - a);
                fl = f.apply(xl);
            }
            functionCalls++;
            iterations++;
        }

        double res = (a + b) / 2;
        double precision = abs(b - a) / 2;

        return new Result(res, functionCalls, precision, Result.search_method_type.GoldenRation, iterations);
    }
}
