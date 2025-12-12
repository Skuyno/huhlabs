package ru.ssau.tk.practiceoop1.Laba3;

import org.joml.Vector2d;
import ru.ssau.tk.practiceoop1.Laba3.Interfaces.FunctionND;

public class Main {
    public static void main(String[] args) {
        FunctionND baseFunction = p -> Math.pow(p.x - 1.0, 2) + Math.pow(p.y + 2.0, 2);

        Vector2d start = new Vector2d(-0.5, -0.5);
        double eps = 1e-6;
        int maxIter = 100;

        MDResult gdInterior = GradientDescent.findExtrema(baseFunction, start, eps, 0.1, maxIter);

        System.out.println(gdInterior);

    }
}