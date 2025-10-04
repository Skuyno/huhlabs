package ru.ssau.tk.practiceoop1.Laba1;



public class Result {
    public enum search_method_type{
        Dichotomy,
        GoldenRation,
        Fibonaci,
        none
    }

    public final double xMin;
    public final int functionCalls;
    public final double precision;
    public final double iterations;
    public final search_method_type methodType;

    public Result(double xMin, int functionCalls, double precision, search_method_type methodType, double iterations) {
        this.xMin = xMin;
        this.functionCalls = functionCalls;
        this.precision = precision;
        this.iterations = iterations;
        this.methodType = methodType;
    }

    @Override
    public String toString(){
        return String.format("x = " + xMin + " calls = " + functionCalls + " precision = " + precision + " method type = " + methodType + " iterations = " + iterations);
    }
}
