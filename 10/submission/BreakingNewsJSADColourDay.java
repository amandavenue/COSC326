import java.util.*;
/**
 * An application class for the Colour of the day etude in
 * COCSC326 SS 2018.
 *
 * @author Daniela Lemow, Amanda Veldman, Jack Manning, Sophie Purdie
 */
public class BreakingNewsJSADColourDay {

    public static String[] calendar;
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("How many days have occured so far?");
        calendar = new String[(sc.nextInt()+1)];
        System.out.println();
        
        calendar[0] = null;
        calendar[1] = "NO COLOUR";
        colourOfTheDay();

        
        for (int i = 1; i < calendar.length; i++) {
            System.out.print("Day " + (i) + ", ");

            switch ((i)%7) {
                case 1 :
                    System.out.print("Monday, ");
                    break;
                case 2 :
                    System.out.print("Tuesday, ");
                    break;
                case 3 :
                    System.out.print("Wednesday, ");
                    break;
                case 4 :
                    System.out.print("Thursday, ");
                    break;
                case 5 :
                    System.out.print("Friday, ");
                    break;
                case 6 :
                    System.out.print("Saturday, ");
                    break;
                case 0 :
                    System.out.print("Sunday, ");
                    break;
            }
            
            
            System.out.print("Colour: " + calendar[i]);

            if (isPrime(i)) {
                System.out.print(" [PRIME]");
            }

            System.out.println();
        }
    
        System.out.println("Holidays in year 1000: " + holidaysInYear(1000));
        System.out.println(threeDayHoliday());
    }

    /**
     * Determines what colour each day should be and
     * sets it accordingly in the global array "calendar".
     */
    public static void colourOfTheDay() {

        for (int i = 2; i < calendar.length; i++) {
            System.out.println(i);
            if (isPrime(i)) {

                switch ((i)%7) {
                    case 1 : case 4 : // Monday and Thursday
                        calendar[i] = "Red";
                        break;
                    case 2 : case 5 : // Tuesday and Friday
                        calendar[i] = "Green";
                        break;
                    case 3 : case 6 : // Wednesday and Saturday
                        calendar[i] = "Blue";
                        break;
                    case 0 : // Sunday
                        calendar[i] = "Gold";
                        break;
                }
            } else {
                calendar[i] = nonPrimeDayV2(i);
            }
        }
    }

    /**
     * Determines what colour a non-prime day should be.
     * @return String a string representing the colour of
     * the non-prime day.
     */

    
    public static String nonPrimeDay(int d) {

        int red = 0;
        int green = 0;
        int blue = 0;

        for (int i = 2; i < d; i++) {
            if (d % i == 0) {
                if (calendar[i].equals("Red")) {
                    red++;
                } else if (calendar[i].equals("Green")) {
                    green++;
                } else if (calendar[i].equals("Blue")) {
                    blue++;
                }
            }
        }

        System.out.println("Day = " + d + " Red = " + red + " Green = " + green + " Blue = " + blue);

        if (red == green && green == blue) {
            return "Gold";
        } else if (red != green && green != blue && blue != red){
            if ( red > green && red > blue ){
                return "Red";
            } else if ( green > red && green > blue ){
                return "Green";
            } else {
                return "Blue";
            }
        } else if (red == green || green == blue || blue == red){
            if (red ==  green){
                return "Blue";
            } else if (green == blue){
                return "Red";
            } else {
                return "Green";
            }
        }
        return "inconclusive";
        
    }
    

    /**
     * Determines what colour a non-prime day should be
     * based on previous day of the same factor.
     * @return String a string representing the colour of
     * the non-prime day.
     */
    public static String nonPrimeDayV2 (int d){

        int red = 0;
        int green = 0;
        int blue = 0;

        for (int i = 2; i < d; i++){
            if (d % i == 0){
                if (calendar[d-i].equals("Red")){
                    red++;
                } else if (calendar[d-i].equals("Green")){
                    green++;
                } else if (calendar[d-i].equals("Blue")){
                    blue++;
                }
            }
        }

        if (red == green && green == blue) {
            return "Gold";
        } else if (red != green && green != blue && blue != red){
            if ( red > green && red > blue ){
                return "Red";
            } else if ( green > red && green > blue ){
                return "Green";
            } else {
                return "Blue";
            }
        } else if (red == green || green == blue || blue == red){
            if (red ==  green){
                return "Blue";
            } else if (green == blue){
                return "Red";
            } else {
                return "Green";
            }
        }
        return "inconclusive";  
    }

    /**
     * Returns a boolean indicating if a number is prime.
     * @return boolean indicates if a number is prime.
     */
    public static boolean isPrime(int n) {

        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the number of holidays ("Gold" days) in
     * a specified year.
     * @return int the number of "Gold" days in a year.
     */
    public static int holidaysInYear(int year) {

        int startDay = ((year * 350)-349);
        int holidays = 0;
        int count = 1;

        for (int i = startDay; i < calendar.length; i++) {
            if (calendar[i].equals("Gold")) {
                holidays++;
            }
        }

        return holidays;
    }

    /**
     * Returns a string indicating the index of the day
     * where the first three day holiday (or three "Gold"
     * days in a row) begins.
     * @return String a string indicating the index of the
     * first three day holiday.
     */
    public static String threeDayHoliday() {

        for (int i = 1; i < calendar.length; i++){
            if (calendar[i].equals("Gold") && calendar[i+1].equals("Gold") && calendar[i+2].equals("Gold")){
                return "The first three day holiday begins on day " + i;
            }
        }
        return "There will never be a three day holiday";
    }

}
