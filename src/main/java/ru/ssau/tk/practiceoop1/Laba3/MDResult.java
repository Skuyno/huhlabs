package ru.ssau.tk.practiceoop1.Laba3;

import org.joml.Vector2d;
import java.util.Locale;

public class MDResult {
    public enum SearchMethodType {
        GradientDescent
    }

    public final Vector2d xMin;
    public final long functionCalls;
    public final double precision;
    public final long iterations;
    public final SearchMethodType methodType;

    public MDResult(Vector2d xMin, long functionCalls, double precision,
                    SearchMethodType methodType, long iterations) {
        this.xMin = new Vector2d(xMin);
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