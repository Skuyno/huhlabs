package ru.ssau.tk.practiceoop1.Laba3;

import ru.ssau.tk.practiceoop1.Laba3.Interfaces.Constraint;
import ru.ssau.tk.practiceoop1.Laba3.Interfaces.FunctionND;

import java.util.List;

public final class PenaltyFunctions {
    private PenaltyFunctions() {
    }

    public static FunctionND interiorPenalty(FunctionND f, List<Constraint> constraints, double lambda) {
        return x -> {
            double penalty = 0.0;
            for (Constraint c : constraints) {
                double value = c.apply(x);
                if (value < 0.0) {
                    penalty -= lambda * Math.log(-value);
                } else {
                    return Double.POSITIVE_INFINITY;
                }
            }
            return f.apply(x) + penalty;
        };
    }

    public static FunctionND exteriorPenalty(FunctionND f, List<Constraint> inequalities,
                                             List<Constraint> equalities, double lambda) {
        return x -> {
            double penalty = 0.0;
            for (Constraint g : inequalities) {
                double v = g.apply(x);
                double violation = Math.max(0.0, v);
                penalty += lambda * violation * violation;
            }
            for (Constraint h : equalities) {
                double v = h.apply(x);
                penalty += lambda * v * v;
            }
            return f.apply(x) + penalty;
        };
    }
}