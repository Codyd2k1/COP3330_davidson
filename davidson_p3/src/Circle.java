public class Circle extends Shape2D {
    double Cradius;

    public Circle(double radius) {
        Cradius = radius;
    }

    public double getArea() {
        double area;
        area = (3.141592653589793) * (Cradius * Cradius);
        return area;
    }

    public String getName() {
        return "circle";
    }
}
