
import java.text.DecimalFormat;

public class Pyramid extends Shape3D {
    double Side1;
    double Side2;
    double Side3;

    public Pyramid(double a, double b, double c) {
        Side1 = a;
        Side2 = b;
        Side3 = c;
    }

    public double getArea() {
        double area;
        area = ((Side1 * Side2) + ((Side1) * Math.sqrt(((Side2 / 2) * (Side2 / 2)) + (Side3 * Side3))) + Side2 * (Math.sqrt(((Side1 / 2) * (Side1 / 2)) + ((Side3) * (Side3)))));
        return area;

    }

    public String getName() {
        return "pyramid";
    }

    public double getVolume()
    {
        double volume;
        volume =  ((Side1*Side2*Side3)/ 3);
        //volume = Math.round(volume * 100.0)/100.0;
        //String Vume = d.format((volume));
        //System.out.println("volume is: "+volume);
        //volume = 0.01 * Math.floor(volume * 100.0);
        //double VRET = Double.parseDouble(Vume);
        return volume;
    }

}
