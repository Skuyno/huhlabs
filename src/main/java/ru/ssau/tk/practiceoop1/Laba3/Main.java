package ru.ssau.tk.practiceoop1.Laba3;

import org.joml.Vector2d;
import ru.ssau.tk.practiceoop1.Laba3.Interfaces.Constraint;
import ru.ssau.tk.practiceoop1.Laba3.Interfaces.FunctionND;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FunctionND baseFunction = p -> Math.pow(p.x - 1.0, 2) + Math.pow(p.y + 2.0, 2);

        Constraint inequality = p -> p.x + p.y - 1.0; // x + y <= 1
        Constraint equality = p -> p.x - p.y;         // x = y

        FunctionND interior = PenaltyFunctions.interiorPenalty(baseFunction, List.of(inequality), 0.1);
        FunctionND exterior = PenaltyFunctions.exteriorPenalty(baseFunction,
                List.of(inequality), List.of(equality), 0.1);

        Vector2d start = new Vector2d(-0.5, -0.5);
        double eps = 1e-6;
        int maxIter = 100;

        MDResult gd = GradientDescent.findExtrema(baseFunction, start, eps, 0.1, maxIter);
        MDResult cg = ConjugateGradient.findExtrema(baseFunction, start, eps, 0.1, maxIter);
        MDResult nr = NewtonRaphson.findExtrema(baseFunction, start, eps, maxIter);

        MDResult gdInterior = GradientDescent.findExtrema(interior, start, eps, 0.1, maxIter);
        MDResult cgInterior = ConjugateGradient.findExtrema(interior, start, eps, 0.1, maxIter);
        MDResult nrInterior = NewtonRaphson.findExtrema(interior, start, eps, maxIter);

        MDResult gdExterior = GradientDescent.findExtrema(exterior, start, eps, 0.1, maxIter);
        MDResult cgExterior = ConjugateGradient.findExtrema(exterior, start, eps, 0.1, maxIter);
        MDResult nrExterior = NewtonRaphson.findExtrema(exterior, start, eps, maxIter);

        System.out.println(gd);
        System.out.println(cg);
        System.out.println(nr);

        System.out.println(gdInterior);
        System.out.println(cgInterior);
        System.out.println(nrInterior);

        System.out.println(gdExterior);
        System.out.println(cgExterior);
        System.out.println(nrExterior);
    }
}