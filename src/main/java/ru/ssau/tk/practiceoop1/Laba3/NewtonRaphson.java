package ru.ssau.tk.practiceoop1.Laba3;

import org.joml.Matrix2d;
import org.joml.Vector2d;
import ru.ssau.tk.practiceoop1.Laba3.Interfaces.FunctionND;

public class NewtonRaphson {
    public static MDResult findExtrema(FunctionND f, Vector2d x0, double eps, int maxIterations) {
        Vector2d x_i = new Vector2d(x0);
        long calls = 0;
        int iterations;

        for (iterations = 0; iterations < maxIterations; iterations++) {
            NumericalDifferentiation.GradientResult gradRes = NumericalDifferentiation.gradient(f, x_i);
            NumericalDifferentiation.HessianResult hessianRes = NumericalDifferentiation.hessian(f, x_i);
            calls += gradRes.evaluations() + hessianRes.evaluations();

            Vector2d grad = gradRes.gradient();
            double[][] h = hessianRes.hessian();

            Matrix2d hessian = new Matrix2d(
                    h[0][0], h[0][1],
                    h[1][0], h[1][1]
            );

            Matrix2d inverse = hessian.invert(new Matrix2d());
            Vector2d step = inverse.transform(new Vector2d(grad));
            Vector2d x_ip1 = new Vector2d(x_i).sub(step);

            double distance = x_ip1.distance(x_i);
            x_i.set(x_ip1);

            if (distance < 2.0 * eps) {
                return new MDResult(new Vector2d(x_i), calls, 0.5 * distance,
                        MDResult.SearchMethodType.NewtonRaphson, iterations + 1);
            }
        }

        return new MDResult(new Vector2d(x_i), calls, 0.0,
                MDResult.SearchMethodType.NewtonRaphson, iterations);
    }
}
