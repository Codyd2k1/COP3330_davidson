import java.text.DecimalFormat;

public class BodyMassIndex {

    double sHeight;
    double sWeight;
    //THIS IS JUST A CONSTRUCTOR DON'T NEED TESTS!
    public BodyMassIndex(double height, double weight)
    {
        sHeight = height;
        sWeight = weight;
        System.out.println("Your user has a height of " + height + " inches and a weight of " + weight +" pounds.");

    }


    public double bmiScore(double height, double weight)
    {
        sHeight = height;
        sWeight = weight;
        DecimalFormat d = new DecimalFormat("##.#");

        double bmiScore = ((703)*(sWeight/(sHeight*sHeight)));
        String bScore = d.format(bmiScore);
        double bmiScoreRet = Double.parseDouble(bScore);
        //just used to test string to int conversion, deleted 9/22/2020, feel free to remove
        //System.out.println("\nDECIMAL ROUNDED BMI SCORE: " + bmiScoreRet);
        return bmiScoreRet;
    }
    public String bmiCategory(double height, double weight)
    {
        sHeight = height;
        sWeight = weight;
        double bmiScore = ((703)*(sWeight/(sHeight*sHeight)));

        String bmiCategory = "";

        if (bmiScore <= 18.5)
        {
            bmiCategory = "Underweight";
        }
        else if (bmiScore >18.5 && bmiScore <=24.9)
        {
            bmiCategory = "Normal Weight";
        }
        else if (bmiScore >= 25 && bmiScore <=29.9)
        {
            bmiCategory = "Overweight";
        }
        else if (bmiScore >=30)
        {
            bmiCategory = "Obesity";
        }
        else
        {
            System.out.println("Invalid BMI.");
        }
        return bmiCategory;
    }




}
