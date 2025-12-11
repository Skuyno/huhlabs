package ru.ssau.tk.practiceoop1.Laba3.Interfaces;

import org.joml.Vector2d;

@FunctionalInterface
public interface FunctionND {
    double apply(Vector2d x);
}
