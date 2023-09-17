public class Geometry {
    public static void main(String[] args) {
        printSquareArea(7.0);
        printSquareArea(5.0);
        printSquareArea(3.0);

        printRectangleAres(3.0, 5.0);
        printRectangleAres(7.0, 9.0);
    }

    private static void printRectangleAres(double a, double b) {
        System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + rectangleArea(a, b));
    }

    private static double rectangleArea(double a, double b) {
        return a * b;
    }

    static void printSquareArea(double side) {
            System.out.println("Площадь квадрата со сторовной " + side + " = " + squareAre(side));
        }

    private static double squareAre(double a) {
        return a * a;
    }
}
