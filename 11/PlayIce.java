import java.util.*;

public class PlayIce{

    //global variable for the alphabet
    //global variable for the rules

    public static void main (String[] args){

        Scanner sc = new Scanner(System.in);
        
        Boolean firstLine = false;
        Boolean rules = false;
        Boolean blank = false;

        ArrayList<String> rule = new ArrayList<String>();

        while (sc.hasNextLine()){
            if (firstLine == false){
                //assign sc.nextLine() to the alphabet structure
                String first = sc.nextLine();
                System.out.println("first line : " + first);
                firstLine = true;
            }
            while (!((sc.nextLine().trim.isEmpty())){
                rule.add(sc.nextLine());
                //add rules to the forbidden strings and exceptions structure
            }
            if (sc.nextLine() == "\n"){
                rules = true;
                System.out.println(rule);
                System.out.println();
            }
            while (sc.hasNextLine()){
                System.out.println(sc.nextLine());
                if (sc.hasNextInt()){
                    //method that generates valid strings of length sc.nextInt
                } else {
                    //method to check if sc.nextLine is valid or not
                }
            }
        }
    }

    public static int generator(int len){

        ArrayList<String> valid = new ArrayList<String>();

        //generate all valid strings and add then to ArrayList valid

        return valid.size();
    }

    public static boolean validity (String s){

        //check if the string is valid against the alphabet
        //check if s is valid according to the rules

        return true;

    }
}
        
                
