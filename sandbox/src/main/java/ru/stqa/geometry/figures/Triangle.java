package ru.stqa.geometry.figures;

public class Triangle {

    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double perimeter() {
        return this.a + this.b + this.c;
    }

    public double area() {
        double p = this.perimeter();

        return Math.sqrt((p/2) * ((p/2) - this.a) * ((p/2) - this.b) * ((p/2) - this.c));
    }
}
