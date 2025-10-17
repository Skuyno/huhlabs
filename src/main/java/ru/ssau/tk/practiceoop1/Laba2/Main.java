package ru.ssau.tk.practiceoop1.Laba2;

import org.joml.Vector2d;
import ru.ssau.tk.practiceoop1.Laba2.Interfaces.FunctionND;

public class Main {
    public static void main(String[] args) {
        FunctionND f = p -> (p.x - 2.0) * (p.x - 2.0) + (p.y - 3.0) * (p.y - 3.0);

        Vector2d lhs = new Vector2d(0.0, 0.0);
        Vector2d rhs = new Vector2d(5.0, 5.0);
        double eps = 1e-6;
        int maxIter = 1000;

        ResultND dichotomyResult = MDDichotomy.findExtrema(f, lhs, rhs, eps, maxIter);
        ResultND goldenResult = MDGoldenRatio.findExtrema(f, lhs, rhs, eps, maxIter);

        System.out.println(dichotomyResult);
        System.out.println(goldenResult);
    }
}
