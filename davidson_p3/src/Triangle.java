public class Triangle extends Shape2D {
    double Base;
    double Height;

    public Triangle(double base, double height) {
        Base = base;
        Height = height;
    }

    public double getArea() {
        double area;
        area = ((.5) * Base * Height);
        return area;
    }

    public String getName() {
        return "triangle";
    }

}
