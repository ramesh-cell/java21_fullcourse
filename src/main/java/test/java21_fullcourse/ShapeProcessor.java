package test.java21_fullcourse;

// 2. Record pattern & pattern matching with switch

public class ShapeProcessor {

    public static String describeShape(Shape shape) {

        return switch (shape) {

            case Circle(double r) -> "Circle with radius " + r;

            case Square(double s) -> "Square with side " + s;
        };
    }
}