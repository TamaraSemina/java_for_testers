package ru.stqa.geometry.figures;

import java.util.Objects;

public class Triangle {

    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;

        if (a < 0 || b < 0 || c < 0) {
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }

        if (a + b < c || a + c < b || b + c < a) {
            throw new IllegalArgumentException("Sum of the lengths of any two sides should be greater than the length of the third side");
        }
    }

    public double perimeter() {
        return this.a + this.b + this.c;
    }

    public double area() {
        double p = this.perimeter();

        return Math.sqrt((p/2) * ((p/2) - this.a) * ((p/2) - this.b) * ((p/2) - this.c));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(this.a, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0
                || Double.compare(this.b, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0 && Double.compare(this.a, triangle.c) == 0
                || Double.compare(this.c, triangle.a) == 0 && Double.compare(this.a, triangle.b) == 0 && Double.compare(this.b, triangle.c) == 0
                || Double.compare(this.a, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0 && Double.compare(this.b, triangle.c) == 0
                || Double.compare(this.b, triangle.a) == 0 && Double.compare(this.a, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0
                || Double.compare(this.c, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.a, triangle.c) == 0;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
