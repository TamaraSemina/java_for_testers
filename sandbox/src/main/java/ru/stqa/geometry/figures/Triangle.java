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
        var p = new Triangle(this.a, this.b, this.c);
        double p2 = p.perimeter();

        return Math.sqrt((p2/2) * ((p2/2) - this.a) * ((p2/2) - this.b) * ((p2/2) - this.c));
    }
}
