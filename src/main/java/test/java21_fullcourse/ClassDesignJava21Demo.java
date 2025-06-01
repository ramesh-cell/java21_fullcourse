package test.java21_fullcourse;

public class ClassDesignJava21Demo {
    public static void main(String[] args) {

        // 1. Sealed + Record + Pattern Matching

        Shape shape1 = new Circle(2.5);
        Shape shape2 = new Square(4);

        System.out.println(ShapeProcessor.describeShape(shape1));
        System.out.println(ShapeProcessor.describeShape(shape2));

        // 2. ScopedValue Demo
        ScopedContextDemo.runWithContext("nathan");
    }
}