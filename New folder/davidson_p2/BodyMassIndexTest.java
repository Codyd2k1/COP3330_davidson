import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BodyMassIndexTest {
    //the bmi will take in user height and weight, and calculate bmi score using 703 * weight/height^2.


    @Test
    public void testGetBMIScore()
    {
        BodyMassIndex c = new BodyMassIndex(72, 180);
        double answer = c.bmiScore(c.sHeight, c.sWeight);

        assertEquals(24.4, answer);
    }

    @Test
    public void testBmiCat_normalweight()
    {
        BodyMassIndex c = new BodyMassIndex(72, 180);
        String answer = c.bmiCategory(c.sHeight,c.sWeight);

        assertEquals("Normal Weight", answer);
    }


    @Test
    public void testBmiCat_underweight()
    {
        BodyMassIndex c = new BodyMassIndex(72, 100);
        String answer = c.bmiCategory(c.sHeight,c.sWeight);

        assertEquals("Underweight", answer);

    }

    @Test
    public void testBmiCat_overweight()
    {
        BodyMassIndex c = new BodyMassIndex(72, 215);
        String answer = c.bmiCategory(c.sHeight,c.sWeight);

        assertEquals("Overweight", answer);

    }

    @Test
    public void testBmiCat_Obesity()
    {
        BodyMassIndex c = new BodyMassIndex(72, 500);
        String answer = c.bmiCategory(c.sHeight,c.sWeight);

        assertEquals("Obesity", answer);

    }
}