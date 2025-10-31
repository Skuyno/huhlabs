package ru.ssau.tk.practiceoop1.Laba2;

import org.joml.Vector2d;
import ru.ssau.tk.practiceoop1.Laba2.Interfaces.FunctionND;

public class MDCoordinateDescent {
    public static MDResult findExtrema(FunctionND f, Vector2d x0, double eps, double lambda, int maxIterations) {
        Vector2d x_i  = new Vector2d(x0);
        Vector2d x_ip1;

        final Vector2d e0 = new Vector2d(1.0, 0.0);
        final Vector2d e1 = new Vector2d(0.0, 1.0);

        int iterations = 0;      // внешние итерации (шаги по координатам)
        int evaluations = 0;     // вызовы целевой функции

        Vector2d cycleStart = new Vector2d(x_i); // точка в начале цикла (2 шага)

        for (; iterations != maxIterations; iterations++) {
            final Vector2d ek = (iterations % 2 == 0) ? e0 : e1;

            // монотонность около x_i вдоль ek
            Vector2d xl = new Vector2d(x_i).sub(new Vector2d(ek).mul(eps));
            Vector2d xr = new Vector2d(x_i).add(new Vector2d(ek).mul(eps));

            double fl = f.apply(xl);
            double fr = f.apply(xr);
            evaluations += 2;

            // отрезок [a, b] вдоль ek длиной lambda
            Vector2d a = new Vector2d(x_i);
            Vector2d b = (fl > fr)
                    ? new Vector2d(x_i).add(new Vector2d(ek).mul(lambda))  // +ek
                    : new Vector2d(x_i).sub(new Vector2d(ek).mul(lambda)); // -ek

            // линейный поиск на [a, b] (золотое сечение)
            MDResult line = MDGoldenRatio.findExtrema(f, a, b, eps, 100); // 100 достаточно
            evaluations += line.functionCalls;

            x_ip1 = new Vector2d(line.xMin);
            x_i.set(x_ip1);

            // проверяем остановку ТОЛЬКО после полного цикла по координатам (2D → каждые 2 шага)
            if ((iterations % 2) == 1) { // сделали шаг по x и по y
                if (x_i.distance(cycleStart) < 2.0 * eps) {
                    double precision = 0.5 * x_i.distance(cycleStart);
                    return new MDResult(new Vector2d(x_i), evaluations, precision,
                            MDResult.SearchMethodType.CoordinateDescent, iterations + 1);
                }
                cycleStart.set(x_i); // новый цикл
            }
        }

        // лимит по итерациям
        double precision = 0.0;
        return new MDResult(new Vector2d(x_i), evaluations, precision,
                MDResult.SearchMethodType.CoordinateDescent, iterations);
    }
}
