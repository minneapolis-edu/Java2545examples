package week3_methods.tires;

import static input.InputUtils.doubleInput;
import static input.InputUtils.stringInput;

/**
 * Created by clara on 2019-08-23.
 * Since we'll need to repeat the calculation many times, move it into a method so
 * that method can be called repeatedly, as many times as needed
 */

public class Tires2 {
    
    public static void main(String[] args) {
        String report = getTireInfo();
        System.out.println(report);
    }
    
    public static String getTireInfo() {
        String tireName = stringInput("Enter name of tire");
        double price = doubleInput("Enter price for " + tireName);
        double miles = doubleInput("Enter warranty miles for " + tireName);
        double pricePerMile = price / miles;
        return "The " + tireName + " tire costs " + pricePerMile + " dollars per miles.";
    }
}



