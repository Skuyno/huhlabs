package ru.ssau.tk.practiceoop1.Laba3;

import org.joml.Vector2d;
import ru.ssau.tk.practiceoop1.Laba3.Interfaces.FunctionND;

public final class NumericalDifferentiation {
    private static final double DX = 1e-6;

    private NumericalDifferentiation() { }

    public record GradientResult(Vector2d gradient, long evaluations) {}

    public static GradientResult gradient(FunctionND f, Vector2d point) {
        Vector2d grad = new Vector2d();

        Vector2d shifted = new Vector2d(point);
        shifted.x = point.x + DX;
        double fxPlus = f.apply(shifted);

        shifted.x = point.x - DX;
        double fxMinus = f.apply(shifted);

        shifted.y = point.y + DX;
        double fyPlus = f.apply(shifted);

        shifted.y = point.y - DX;
        double fyMinus = f.apply(shifted);

        grad.x = (fxPlus - fxMinus) / (2.0 * DX);
        grad.y = (fyPlus - fyMinus) / (2.0 * DX);

        System.out.println(grad.x + " " + grad.y);

        return new GradientResult(grad, 4); // 4 вызова функции
    }
}
