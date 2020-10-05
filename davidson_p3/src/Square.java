public class Square extends Shape2D {
    double Base;

    public Square(double size) {
        Base = size;
    }

    public double getArea() {
        double area = (Base * Base);
        return area;
    }

    public String getName() {
        return "square";
    }

}
