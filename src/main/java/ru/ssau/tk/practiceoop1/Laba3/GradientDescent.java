package ru.ssau.tk.practiceoop1.Laba3;

import org.joml.Vector2d;
import ru.ssau.tk.practiceoop1.Laba3.Interfaces.FunctionND;

public class GradientDescent {
    public static MDResult findExtrema(FunctionND f, Vector2d x0, double eps, double lamba, int maxIterations) {
        Vector2d x_i = new Vector2d(x0);
        long calls = 0;
        int iterations;

        for (iterations = 0; iterations < maxIterations; iterations++) {
            NumericalDifferentiation.GradientResult gradRes = NumericalDifferentiation.gradient(f, x_i);
            calls += gradRes.evaluations();
            Vector2d grad = gradRes.gradient();

            Vector2d step = new Vector2d(grad).mul(-lamba);
            Vector2d x_ip1 = new Vector2d(x_i).add(step);

            double distance = x_ip1.distance(x_i);
            x_i.set(x_ip1);

            if(distance < 2.0 * eps) { // Проверяем критерий сходимости
                return new MDResult(new Vector2d(x_i), calls, 0.5 * distance, MDResult.SearchMethodType.GradientDescent, iterations + 1);
            }
        }
        return new MDResult(new Vector2d(x_i), calls, 0.0, MDResult.SearchMethodType.GradientDescent, iterations);
    }
}
