package ru.ssau.tk.practiceoop1.Laba1;

public class Result {
    public final double xMin;
    public final int functionCalls;
    public final double precision;

    public Result(double xMin, int functionCalls, double precision) {
        this.xMin = xMin;
        this.functionCalls = functionCalls;
        this.precision = precision;
    }

    @Override
    public String toString(){
        return String.format("x = " + xMin + " calls = " + functionCalls + " precision = " + precision);
    }
}
