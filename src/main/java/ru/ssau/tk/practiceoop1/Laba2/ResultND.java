package ru.ssau.tk.practiceoop1.Laba2;

import org.joml.Vector2d;
import java.util.Locale;

public class ResultND {
    public enum SearchMethodType {
        Dichotomy,
        GoldenRatio,
        none
    }

    public final Vector2d xMin;          // найденная точка минимума
    public final long functionCalls;     // количество вычислений функции
    public final double precision;       // достигнутая точность (|rhs - lhs| / 2)
    public final long iterations;        // количество итераций
    public final SearchMethodType methodType;

    public ResultND(Vector2d xMin, long functionCalls, double precision,
                    SearchMethodType methodType, long iterations) {
        this.xMin = new Vector2d(xMin); // копия, чтобы не изменить извне
        this.functionCalls = functionCalls;
        this.precision = precision;
        this.iterations = iterations;
        this.methodType = methodType;
    }

    @Override
    public String toString() {
        return String.format(Locale.US,
                "{\n\t\"ResultND\": {\n" +
                        "\t\t\"method type\":    \"%s\",\n" +
                        "\t\t\"iterations\":      %d,\n" +
                        "\t\t\"function probes\": %d,\n" +
                        "\t\t\"precision\":       %.6e,\n" +
                        "\t\t\"result\":          (%.6f, %.6f)\n" +
                        "\t}\n}",
                methodType.toString(), iterations, functionCalls, precision,
                xMin.x, xMin.y);
    }
}
