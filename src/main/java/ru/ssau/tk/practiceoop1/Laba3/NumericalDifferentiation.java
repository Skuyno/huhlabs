package ru.ssau.tk.practiceoop1.Laba3;

import org.joml.Vector2d;
import ru.ssau.tk.practiceoop1.Laba3.Interfaces.FunctionND;

public final class NumericalDifferentiation {
    private static final double DX = 1e-6;

    private NumericalDifferentiation() {
    }

    public record GradientResult(Vector2d gradient, long evaluations) {}

    public record HessianResult(double[][] hessian, long evaluations) {}

    public static GradientResult gradient(FunctionND f, Vector2d point) {
        Vector2d grad = new Vector2d();

        Vector2d shifted = new Vector2d(point);
        shifted.x = point.x + DX;
        double fxPlus = f.apply(shifted);

        shifted.x = point.x - DX;
        double fxMinus = f.apply(shifted);

        shifted.set(point.x, point.y + DX);
        double fyPlus = f.apply(shifted);

        shifted.set(point.x, point.y - DX);
        double fyMinus = f.apply(shifted);

        grad.x = (fxPlus - fxMinus) / (2.0 * DX);
        grad.y = (fyPlus - fyMinus) / (2.0 * DX);

        return new GradientResult(grad, 4); // 4 вызова функции
    }

    public static HessianResult hessian(FunctionND f, Vector2d point) {
        double[][] h = new double[2][2];
        long calls = 0;

        // f(x + h, y), f(x - h, y)
        Vector2d shifted = new Vector2d(point);
        shifted.x = point.x + DX;
        double f_x_plus = f.apply(shifted);
        shifted.x = point.x - DX;
        double f_x_minus = f.apply(shifted);
        calls += 2;

        // f(x, y + h), f(x, y - h)
        shifted.set(point.x, point.y + DX);
        double f_y_plus = f.apply(shifted);
        shifted.set(point.x, point.y - DX);
        double f_y_minus = f.apply(shifted);
        calls += 2;

        // f(x + h, y + h), f(x + h, y - h), f(x - h, y + h), f(x - h, y - h)
        shifted.set(point.x + DX, point.y + DX);
        double f_xp_yp = f.apply(shifted);
        shifted.set(point.x + DX, point.y - DX);
        double f_xp_ym = f.apply(shifted);
        shifted.set(point.x - DX, point.y + DX);
        double f_xm_yp = f.apply(shifted);
        shifted.set(point.x - DX, point.y - DX);
        double f_xm_ym = f.apply(shifted);
        calls += 4;

        // базовое значение в точке
        double f0 = f.apply(point);
        calls += 1;

        // second derivatives
        h[0][0] = (f_x_plus - 2.0 * f0 + f_x_minus) / (DX * DX);
        h[1][1] = (f_y_plus - 2.0 * f0 + f_y_minus) / (DX * DX);

        // Mixed derivative
        h[0][1] = (f_xp_yp - f_xp_ym - f_xm_yp + f_xm_ym) / (4.0 * DX * DX);
        h[1][0] = h[0][1];

        return new HessianResult(h, calls);
    }
}
