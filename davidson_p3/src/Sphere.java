public class Sphere extends Shape3D {

        double Radius;
        public Sphere(double size)
        {
            Radius = size;
        }
        public double getArea()
        {
            double area;
            area = (4 * (3.141592653589793) * (Radius*Radius));
            return area;
        }
        public String getName()
        {
            return "sphere";
        }
        public double getVolume()
        {
            return (((1.3333333333)*(3.141592653589793))*(Radius*Radius*Radius));
        }

}
