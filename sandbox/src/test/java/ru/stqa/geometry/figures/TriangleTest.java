package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTest {

    @Test
    void canCalculatePerimeter() {
        var p = new Triangle(1.0, 2.0, 3.0);
        double result = p.perimeter();
        Assertions.assertEquals(6, result);
    }
}
