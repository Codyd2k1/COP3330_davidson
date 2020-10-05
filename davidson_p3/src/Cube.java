public class Cube extends Shape3D {
    double Base;

    public Cube(double base) {
        Base = base;
    }

    public double getArea() {
        double area = (6 * (Base * Base));
        return area;
    }

    public String getName() {
        return "cube";
    }

    public double getVolume() {
        return Base * Base * Base;
    }
}
