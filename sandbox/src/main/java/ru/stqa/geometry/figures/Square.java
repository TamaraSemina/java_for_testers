package ru.stqa.geometry.figures;

public class Square {
    public static void printSquareArea(double side) {
        String text = String.format("Площадь квадрата со стороной %f = %f", side, squareAre(side));
            System.out.println(text);
        }

    private static double squareAre(double a) {
        return a * a;
    }
}
