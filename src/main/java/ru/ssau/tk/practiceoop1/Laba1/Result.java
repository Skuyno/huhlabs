package ru.ssau.tk.practiceoop1.Laba1;


import java.util.Locale;

public class Result {
    public enum search_method_type{
        Dichotomy,
        GoldenRation,
        Fibonacci,
        none
    }

    public final double xMin;
    public final long functionCalls;
    public final double precision;
    public final long iterations;
    public final search_method_type methodType;

    public Result(double xMin, int functionCalls, double precision, search_method_type methodType, long iterations) {
        this.xMin = xMin;
        this.functionCalls = functionCalls;
        this.precision = precision;
        this.iterations = iterations;
        this.methodType = methodType;
    }

    @Override
    public String toString(){
        return String.format(Locale.US,"{\n\t\"Result\":\n\t{" +
                        "\n\t\"method type\":    \"%s\"," +
                        "\n\t\"iterations\":      %d" +
                        "\n\t\"function probes\": %d," +
                        "\n\t\"precision\":       %.6e," +
                        "\n\t\"result\":          %.3f\n\t}\n}",
                methodType.toString(), iterations, functionCalls, precision, xMin);
    }
}
