import java.util.ArrayList;
import java.util.Scanner;
public class App {


    public static double getUserHeight()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter user Height in inches: ");
        double userHeight = input.nextDouble();

        return userHeight;

    }

    public static double getUserWeight()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("\nEnter user Weight: ");
        double userWeight = input.nextDouble();

        return userWeight;

    }

    public static boolean moreInput()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Y to add user, Enter N to continue: ");
        char yesOrNo = input.next().charAt(0);
        char True = 'Y';
        char False = 'F';
        if (yesOrNo== True)
        {
         return true;
        }
        else
        {
            return false;
        }
    }

    public static void displayBmiInfo(BodyMassIndex bmi)
    {
        System.out.println("\nBMI SCORE: "+ bmi.bmiScore(bmi.sHeight,bmi.sWeight));
        System.out.println("\nBMI CATEGORY: "+ bmi.bmiCategory(bmi.sHeight,bmi.sWeight));
    }
    public static void displayBmiStatistics(ArrayList<BodyMassIndex> bmiData)
    {
        int a;
        double totalBmiScore = 0;
        for( a = 0; a < bmiData.size(); a++)
        {
            totalBmiScore = totalBmiScore + bmiData.get(a).bmiScore(bmiData.get(a).sHeight, bmiData.get(a).sWeight);

            System.out.println("\nCurrent BMI Total Score: " + totalBmiScore);
        }
        totalBmiScore = totalBmiScore/bmiData.size();
        System.out.println("\nAverage total BMI Score: " + totalBmiScore);
    }

    public static void main(String[] args) {
        ArrayList<BodyMassIndex> bmiData = new ArrayList<BodyMassIndex>();

        while (moreInput()) {
            double height = getUserHeight();
            double weight = getUserWeight();

            BodyMassIndex bmi = new BodyMassIndex(height, weight);
            bmiData.add(bmi);

            displayBmiInfo(bmi);
        }

        displayBmiStatistics(bmiData);
    }
}
