package ru.ssau.tk.practiceoop1.Laba2;

import org.joml.Vector2d;
import ru.ssau.tk.practiceoop1.Laba2.Interfaces.FunctionND;

public class MDDichotomy {

    public static MDResult findExtrema(FunctionND f, Vector2d a, Vector2d b, double eps, int maxIterations) {
        Vector2d left = new Vector2d(a);
        Vector2d right = new Vector2d(b);

        Vector2d dir = new Vector2d(right).sub(left);
        dir = dir.div(right.distance(left)).mul(eps/10);

        int evaluations = 0;
        int iteration = 0;

        while (iteration < maxIterations && left.distance(right) > 2.0 * eps) {
            // center = (left + right)/2
            Vector2d center = new Vector2d(left).add(right).mul(0.5);

            Vector2d leftProbe = new Vector2d(center).sub(dir);
            Vector2d rightProbe = new Vector2d(center).add(dir);

            double fLeft = f.apply(leftProbe);
            double fRight = f.apply(rightProbe);
            evaluations += 2;

            if (fRight > fLeft) {
                right.set(center);
            } else {
                left.set(center);
            }

            iteration++;
        }

        double precision = 0.5 * left.distance(right);
        Vector2d result = new Vector2d(left).add(right).mul(0.5);

        return new MDResult(result, evaluations, precision,
                MDResult.SearchMethodType.Dichotomy, iteration);
    }
}