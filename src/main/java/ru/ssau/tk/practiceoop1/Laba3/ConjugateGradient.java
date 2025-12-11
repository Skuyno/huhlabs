package ru.ssau.tk.practiceoop1.Laba3;

import org.joml.Vector2d;
import ru.ssau.tk.practiceoop1.Laba3.Interfaces.FunctionND;

public class ConjugateGradient {
    public static MDResult findExtrema(FunctionND f, Vector2d x0, double eps,
                                       double lambda, int maxIterations) {
        Vector2d x_i = new Vector2d(x0);

        NumericalDifferentiation.GradientResult grad0 = NumericalDifferentiation.gradient(f, x_i);
        long calls = grad0.evaluations();
        Vector2d s_i = new Vector2d(grad0.gradient()).mul(-1.0);

        int iterations;
        for (iterations = 0; iterations < maxIterations; iterations++) {
            Vector2d x_ip1 = new Vector2d(x_i).add(new Vector2d(s_i).mul(lambda));
            double distance = x_ip1.distance(x_i);
            x_i.set(x_ip1);

            if (distance < 2.0 * eps) {
                return new MDResult(new Vector2d(x_i), calls, 0.5 * distance,
                        MDResult.SearchMethodType.ConjugateGradient, iterations + 1);
            }

            NumericalDifferentiation.GradientResult gradRes = NumericalDifferentiation.gradient(f, x_i);
            calls += gradRes.evaluations();
            Vector2d grad = gradRes.gradient();

            double prevGradNormSq = grad0.gradient().lengthSquared();
            if (prevGradNormSq == 0.0) {
                return new MDResult(new Vector2d(x_i), calls, 0.5 * distance,
                        MDResult.SearchMethodType.ConjugateGradient, iterations + 1);
            }

            double omega = grad.lengthSquared() / prevGradNormSq;
            s_i.mul(omega).add(new Vector2d(grad).mul(-1.0));

            grad0 = gradRes;
        }

        return new MDResult(new Vector2d(x_i), calls, 0.0,
                MDResult.SearchMethodType.ConjugateGradient, iterations);
    }
}
