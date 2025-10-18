package ru.ssau.tk.practiceoop1.Laba2;

import org.joml.Vector2d;
import ru.ssau.tk.practiceoop1.Laba2.Interfaces.FunctionND;

public class MDGoldenRatio {

    public static ResultND findExtrema(FunctionND f, Vector2d a, Vector2d b, double eps, int maxIterations) {
        final double ψ = (Math.sqrt(5)-1)/2;

        Vector2d left = new Vector2d(a);
        Vector2d right = new Vector2d(b);

        Vector2d xr = new Vector2d(right).sub(left).mul(ψ).add(left);
        Vector2d xl = new Vector2d(right).sub(left).mul(ψ).negate().add(right);

        double fl = f.apply(xl);
        double fr = f.apply(xr);

        int evaluations = 2;
        int iteration = 0;

        while (iteration < maxIterations && left.distance(right) > 2.0 * eps) {
            if (fl > fr) {
                left.set(xl);
                xl.set(xr);
                fl = fr;

                xr = new Vector2d(right).sub(left).mul(ψ).add(left);
                fr = f.apply(xr);
            } else {
                right.set(xr);
                xr.set(xl);
                fr = fl;

                xl = new Vector2d(right).sub(left).mul(ψ).negate().add(right);
                fl = f.apply(xl);
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
