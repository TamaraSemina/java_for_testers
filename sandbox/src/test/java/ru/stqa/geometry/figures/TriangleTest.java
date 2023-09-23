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

    @Test
    void canCalculateArea () {
        var a = new Triangle(26.0, 30.0, 28.0);
        double result = a.area();
        Assertions.assertEquals(336, result);
    }

    @Test
    void cannotCreateTriangleWithNegativeSide () {

        try {
            new Triangle(-26.0, 30.0, 28.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }
}
