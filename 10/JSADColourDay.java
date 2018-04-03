public class JSADColourDay{

    public static String[] calander = new String [20];
    
    public static void main (String [] args){

        calander[0] = "NO COLOUR";
        colours(calander);

    }

    public static String[] colours(String[] c){

        // checks which days in calander will be primes
        for (int i = 1; i < c.length; i++){
            if ((i+1)%2 == 0){
                for (int j = 3; j*j <= (i+1); j+=2){
                    if ((i+1)%j == 0){
                        //the index is not a prime
                        c[i] = factors(i);
                    }
                }
            } else {
                // works out what day of the week a day is
                switch ((i+1)%7){
                    case 1 : case 4 : // Monday and Thursday
                        c[i] = "Red";
                        break;
                    case 2 : case 5 : // Tuesday and Friday
                        c[i] = "Green";
                        break;
                    case 3 : case 6 : // Wednesday and Saturday
                        c[i] = "Blue";
                        break;
                    case 0 : //Sunday
                        c[i] = "Gold";
                        // Day 7 is the only prime Sunday
                        break;
                }
            }
        }
    }

    public static String factors(int d){
        int red = 0;
        int green = 0;
        int blue = 0;

        // work out all the factors of d
        int i = 2;
        while (i <= d+1){
            if ((d+1)%i == 0){ //can this be more efficient?
                if (calander[d] == "Red"){
                    red ++;
                } else if (calander[d] == "Green"){
                    green++;
                } else if (calander[d] = "Blue"){
                    blue++;
                }
            }
            i++;
        }

        if (red == green && green == blue){
            return "Gold";
        } else if (red == green || green == blue || blue == red){
            if (red == green){
                return "Blue";
            } else if (green == blue){
                return "Red";
            } else {
                return "Green";
            }
        } else {
            //max of red, blue, green
    }
}
