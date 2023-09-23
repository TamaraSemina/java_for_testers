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

    @Test
    void testEquality() {
        var t1 = new Triangle(26.0, 30.0, 28.0);
        var t2 = new Triangle(26.0, 30.0, 28.0);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality2() {
        var t1 = new Triangle(26.0, 30.0, 28.0);
        var t2 = new Triangle(28.0, 26.0, 30.0);
        var t3 = new Triangle(30.0, 28.0, 26.0);
        Assertions.assertEquals(t1, t2);
        Assertions.assertEquals(t2, t3);
    }
}
