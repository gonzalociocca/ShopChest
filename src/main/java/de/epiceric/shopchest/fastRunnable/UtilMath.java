package de.epiceric.shopchest.fastRunnable;

/**
 * Created by ciocca on 11/12/16.
 */
import java.text.DecimalFormat;
import java.util.Random;
import org.bukkit.util.Vector;

public class UtilMath
{
    public static Random random = new Random();

    public static double trim(int degree, double d)
    {
        String format = "#.#";

        for (int i = 1; i < degree; i++) {
            format = format + "#";
        }
        DecimalFormat twoDForm = new DecimalFormat(format);
        return Double.valueOf(twoDForm.format(d)).doubleValue();
    }

    public static int r(int i)
    {
        return random.nextInt(i);
    }

    public static double offset(Vector a, Vector b)
    {
        return a.subtract(b).length();
    }
}