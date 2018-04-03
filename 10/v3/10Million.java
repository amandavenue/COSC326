import java.util.*;

public class 10Million{
    public static void main (String[] args){

        String[] thisYear = new String[350];
        long firstDay = 3499999651L;
        long lastDay = 3500000000L;
        int holidays = 0;
        int index = -1;

        //make sure the colours list is 350 big so we can add at indexes later on
        //ArrayList<String> coloursOfNonPrime = new ArrayList<String>(350);

        // iterate through all of the big days
        for (long day = firstDay; day <= lastDay; day++){
            index++;

            // if the current big day is a prime add it's colour to the final array
            if (isPrimeLong(day)){
                thisYear[index] = primeLongColours(day);
                //coloursOfNonPrime(index, primeLongColours(day));
                
            // if the current big day is not a prime
            } else {

                // work out all the primes of this big day number
                ArrayList<Long> factorsOfNonPrime = factorDays(day);

                //make sure the colours list is 350 big so we can add at indexes later on
                ArrayList<String> coloursOfNonPrime = new ArrayList<String>(350);

                
                // iterate through all of the factors
                for (int n = 0; n < factorsOfNonPrime.size(); n++){

                    // if a factor is a prime allocate its colour and add it to the colour array
                    if (isPrimeLong((factorsOfNonPrime.get(n).longValue()))){ 
                        coloursOfNonPrime.add(primeLongColours(factorsOfNonPrime.get(n).longValue()));

                    // if the factor isnt a prime, work out its colour from its factors
                    } else {

                        // initialise a count for each colour a factor can be (not gold though)
                        int red = 0;
                        int green = 0;
                        int blue = 0;
                        
                        //iterate through the list of factors that are less than the current factor
                        for (int j = n-1; j > 0; j--){

                            // if the current factor is divisible by a number before it in the list
                            if (factorsOfNonPrime.get(n) % factorsOfNonPrime.get(j) == 0){

                                // increase the count for the divisible factors colour
                                if (coloursOfNonPrime.get(j).equals("Red")){
                                    red++;
                                } else if (coloursOfNonPrime.get(j).equals("Green")){
                                    green++;
                                } else if (coloursOfNonPrime.get(j).equals("Blue")){
                                    blue++;
                                }
                            }
                        }
                        
                        // this will be the final colour to be added to the full year list
                        String colour = ""; 

                        //work out the colour this factor should be
                        if (red == green && green == blue) {
                            colour = "Gold";
                        } else if (red == green || (blue < green && blue < red)) {
                            colour = "Blue";
                        } else if (green == blue || (red < green && red < blue)) {
                            colour = "Red";
                        } else if (blue == red || (green < red && green < blue)) {
                            colour = "Green";
                        }

                        //add this current factors colour to the list of colours at its correct index
                        coloursOfNonPrime.add(n, colour);
                    }
                    //update the year array of colours
                    thisYear[index] = coloursOfNonPrime.get(n);
                }
            } 
        }

        System.out.println("Length of the year = " + thisYear.length);
        // count how many days in the year are gold
        for (int i = 0; i < thisYear.length; i++){
            if (thisYear[i].equals("Gold")){
                holidays++;
            }
            System.out.println("Day " + i + "'s colour is " + thisYear[i]);
        }
        
        
        // Print out how many holidays there are in the year 
        System.out.println("There are " +  holidays + " holidays in the 10M-th year");
        
    }
    
    // work out if a given long is a prime or not
    public static boolean isPrimeLong(long n){

        for (long i = 2; i*i  <= n; i++){
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }

    // work out the factors of a given long
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
        Collections.sort(factors); 
        return factors;
    }

    // work out the colour of a given prime number
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
    
}
