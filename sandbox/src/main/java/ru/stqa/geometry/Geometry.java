package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;

import java.util.List;
import java.util.function.Consumer;

public class Geometry {
    public static void main(String[] args) {
        var squares = List.of(new Square(7.0), new Square (5.0), new Square (3.0));
//        for (Square square : squares) {
//            Square.printSquareArea(square);
//        }

//        Consumer<Square> print = (square) -> {
//            Square.printSquareArea(square);
//            };
//        Consumer<Square> print = square -> Square.printSquareArea(square);
//        squares.forEach(print);
//        Consumer<Square> print = Square::printSquareArea;
//        squares.forEach(print);
        squares.forEach(Square::printSquareArea);

//        Rectangle.printRectangleAres(3.0, 5.0);
//        Rectangle.printRectangleAres(7.0, 9.0);
    }

}
