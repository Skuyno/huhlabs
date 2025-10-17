package ru.ssau.tk.practiceoop1.Laba2;

import org.joml.Vector2d;
import ru.ssau.tk.practiceoop1.Laba2.Interfaces.FunctionND;

public class MDGoldenRatio {

    public static ResultND findExtrema(FunctionND f, Vector2d lhs, Vector2d rhs, double eps, int maxIterations) {
        final double psi = (Math.sqrt(5.0) - 1.0) / 2.0; // ≈ 0.618

        Vector2d left = new Vector2d(lhs);
        Vector2d right = new Vector2d(rhs);

        Vector2d x_r = new Vector2d(right).sub(left).mul(psi).add(left);
        Vector2d x_l = new Vector2d(right).sub(left).mul(psi).negate().add(right);

        double f_l = f.apply(x_l);
        double f_r = f.apply(x_r);

        int evaluations = 2;
        int iteration = 0;

        while (iteration < maxIterations && left.distance(right) > 2.0 * eps) {
            if (f_l > f_r) {
                left.set(x_l);
                x_l.set(x_r);
                f_l = f_r;

                x_r = new Vector2d(right).sub(left).mul(psi).add(left);
                f_r = f.apply(x_r);
            } else {
                right.set(x_r);
                x_r.set(x_l);
                f_r = f_l;

                x_l = new Vector2d(right).sub(left).mul(psi).negate().add(right);
                f_l = f.apply(x_l);
            }

            evaluations++;
            iteration++;
        }

        double precision = 0.5 * left.distance(right);
        Vector2d result = new Vector2d(left).add(right).mul(0.5);

        return new ResultND(result, evaluations, precision,
                ResultND.SearchMethodType.GoldenRatio, iteration);
    }
}
