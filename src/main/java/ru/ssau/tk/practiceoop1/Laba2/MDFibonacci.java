package ru.ssau.tk.practiceoop1.Laba2;

import org.joml.Vector2d;
import ru.ssau.tk.practiceoop1.Laba2.Interfaces.FunctionND;

public class MDFibonacci {
    public static MDResult findExtrema(FunctionND f, Vector2d a, Vector2d b, double eps, int maxIterations) {

        // локальные копии границ отрезка
        Vector2d left = new Vector2d(a);
        Vector2d right = new Vector2d(b);

        // начальная длина
        double condition = left.distance(right) / eps;;
        double fib_t = 0.0, fib_1 = 1.0, fib_2 = 1.0;

        int evaluations = 0;
        int iteration = 0;

        while (fib_2 < condition && iteration < maxIterations) {
            iteration++;
            fib_t = fib_1;
            fib_1 = fib_2;
            fib_2 += fib_t;
        }

        // сегмент (right - left)
        Vector2d seg = new Vector2d(right).sub(left);

        // xl = a + ((fib_2 - fib_1)/fib_2) * (b - a)
        // xr = a + (fib_1 / fib_2) * (b - a)
        Vector2d xl = new Vector2d(seg).mul((fib_2 - fib_1) / fib_2).add(left);
        Vector2d xr = new Vector2d(seg).mul((fib_1) / fib_2).add(left);

        double fl = f.apply(xl);
        double fr = f.apply(xr);
        evaluations += 2;

        for (int index = 0; index < iteration; index++) {
            // сдвиг фибоначчи назад
            fib_t = fib_2 - fib_1;
            fib_2 = fib_1;
            fib_1 = fib_t;

            if (fl > fr) {
                // a = xl;  xl = xr;  xr = a + (fib_1/fib_2)*(b - a)
                left.set(xl);
                xl.set(xr);  fl = fr;

                seg.set(right).sub(left);
                xr.set(seg).mul(fib_1 / fib_2).add(left);

                fr = f.apply(xr);
                evaluations++;
            } else {
                // b = xr;  xr = xl;  xl = a + ((fib_2 - fib_1)/fib_2)*(b - a)
                right.set(xr);
                xr.set(xl);  fr = fl;

                seg.set(right).sub(left);
                xl.set(seg).mul((fib_2 - fib_1) / fib_2).add(left);

                fl = f.apply(xl);
                evaluations++;
            }
        }

        Vector2d res = new Vector2d(left).add(right).mul(0.5);
        double precision = 0.5 * left.distance(right);

        return new MDResult(res, evaluations, precision,
                MDResult.SearchMethodType.Fibonacci, iteration);
    }
}
