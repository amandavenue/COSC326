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

        //Scanner sc = new Scanner(System.in);
        //System.out.println("How many days have occured so far?");
        //calendar = new String[(sc.nextInt()+1)];
        // System.out.println();
        
        //calendar[0] = null;
        //calendar[1] = "NO COLOUR";
        //colourOfTheDay();

        System.out.println("Holidays in the 10,000,000th year: " + tenMillion());
        
        /* for (int i = 1; i < calendar.length; i++) {
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
        */
        
    }

    /*
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
                calendar[i] = nonPrimeDay(i);
            }
        }
    }
    */

    /*
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
        } else if (red == green || (blue < green && blue < red)) {
            return "Blue";
        } else if (green == blue || (red < green && red < blue)) {
            return "Red";
        } else if (blue == red || (green < red && green < blue)) {
            return "Green";
        }

        return "inconclusive";
        
    }
    */
    

    /*
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
        } else if (red == green || (blue < green && blue < red)) {
            return "Blue";
        } else if (green == blue || (red < green && red < blue)) {
            return "Red";
        } else if (blue == red || (green < red && green < blue)) {
            return "Green";
        }

        return "inconclusive";    
    }
    */



    public static int tenMillion(){
        String[] thisYear = new String[350];
        long firstDay = 3499999651L; // Maybe change these to Double because ArrayList operations are easier
        long lastDay = 3500000000L;
        int holidays = 0;
        int index = 0;

        for (long i = firstDay; i < lastDay; i++){  //changed the days to be long to store the large numbers
            //System.out.println("The day is " + i);

             
            if (isPrimeLong(i)){
                thisYear[index] = primeLongColours(i);
                index++;
                //System.out.println("This year's index is " + index);
                
            } else {
                
                System.out.println("Hello I made it to the else");
                ArrayList<Long> factorsOfNonPrime = factorDays(i);
                
                System.out.println("I've never made it here before");
                ArrayList<String> coloursOfNonPrime = new ArrayList<String>();

                System.out.println("This is unchartered teritory for me");
                /*
                for (long n : factorsOfNonPrime){

                    if (isPrimeLong((factorsOfNonPrime.get(n).longValue()))){ 
                        coloursOfNonPrime.add(primeLongColours(factorsOfNonPrime.get(n).longValue()));
                        System.out.println("IS PRIME");
                    } else {
                        int red = 0;
                        int green = 0;
                        int blue = 0;
                        for (long j = n; j > 0; j--){
                            if (factorsOfNonPrime.get(i) % factorsOfNonPrime.get(j) == 0){
                                if (coloursOfNonPrime.get(j).equals("Red")){
                                    red++;
                                } else if (coloursOfNonPrime.get(j).equals("Green")){
                                    green++;
                                } else if (coloursOfNonPrime.get(j).equals("Blue")){
                                    blue++;
                                }
                            }
                        }
                        String colour = "";  // this can all stay the same
                        if (red == green && green == blue) {
                            colour = "Gold";
                        } else if (red == green || (blue < green && blue < red)) {
                            colour = "Blue";
                        } else if (green == blue || (red < green && red < blue)) {
                            colour = "Red";
                        } else if (blue == red || (green < red && green < blue)) {
                            colour = "Green";
                        }
                        coloursOfNonPrime.add(colour);
                    }
                    
                }
                thisYear[index] = coloursOfNonPrime.get(index);
                index++;
                */
            } 
        }
        //for (int i = 0; i < thisYear.length; i++){
        //  if (thisYear[i].equals("Gold")){
        //      holidays++;
        //  }
        //}
        
        return holidays;
    }
    
    public static String primeLongColours(Long l){
        long remainder = l % 7;
        if (remainder == 1 || remainder == 4){ //Monday and Thursday
            return "Red";
        } else if (remainder == 2 || remainder == 5){ //Tuesday and Friday
            return "Green";
        } else if (remainder == 3 || remainder == 6){ // Wednesday and Saturday
            return "Blue";
        } else if (remainder == 0){ // Sunday
            return "Gold";
        }
        return "";
    }
    
    
    public static ArrayList<Long> factorDays(long n){
        ArrayList<Long> factors = new ArrayList<Long>();

        for (long i = 2L; i*i <= n; i++){
            //System.out.println("i = " + i + ", n = " + n);
            if (n % i == 0){
                factors.add(i);
                if (i*i != n){
                    factors.add(n/i);
                }
            }
        }
        //Need to sort the factors
        System.out.println("Factors = " + factors);
        return factors;
    }

    /*
    public static boolean isPrime(int n) {

        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    */

    public static boolean isPrimeLong(long n){
        for (long i = 2L; i*i  <= n; i++){
            if (n % i == 0){
                return false;
            }
        }
        System.out.println("N is prime " + n);
        return true;
    }

    /*
    public static int holidaysInYear(int year) {

        int startDay = ((year * 350)-349);
        int holidays = 0;

        for (int i = startDay; i < calendar.length; i++) {
            if (calendar[i].equals("Gold")) {
                holidays++;
            }
        }

        return holidays;
    }
    */

    /*
    public static String threeDayHoliday() {

        for (int i = 1; i < calendar.length; i++){
            if (calendar[i].equals("Gold") && calendar[i+1].equals("Gold") && calendar[i+2].equals("Gold")){
                return "The first three day holiday begins on day " + i;
            }
        }
        return "There will never be a three day holiday";
    }
    */

}
