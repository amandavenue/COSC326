import java.util.*;
import java.util.regex.*;

/**
   A Class for the Telephone etude in Cosc326 SS 2018.
   Takes a number from stdin and determines if it is a valid number.
   @author Amanda Veldman (4389944)
*/

public class TidyTelephone {

    /** declare a public array list that will be used to check duplicates.
     */
    public static ArrayList<String> numbers = new ArrayList<String>();

    /** Main method takes input from stdin and outputs to stdout.
     */
    public static void main (String [] args){
        
        Scanner sc = new Scanner(System.in);
        String input;
        while (sc.hasNextLine()){
            input = sc.nextLine();
            if (valid(input) != "INV"){
                if (dups(valid(input))){
                    System.out.println(valid(input) + " DUP");
                } else {
                    numbers.add(valid(input));
                    System.out.println(valid(input));
                }
            } else {
                System.out.println(input + " INV");
            }
        }
    }

    public static String valid (String t){

        String prefix;

        Pattern upper = Pattern.compile("[A-Z]");
        Matcher upperFind = upper.matcher(t);
        boolean hasUpper = upperFind.find();

        //initial codes
        if (t.matches("^\\(?(0508)\\)?\\s?\\d{3}[\\s-]?\\d{3}$")){
            prefix = "0508 ";
            t = t.replaceAll("[^A-Z0-9[()]]", "");
            return prefix + format(t.substring(4,10));
        } else if (t.matches("^\\(?(0508)\\)?\\s?[0-9A-Z]{3}[\\s-]?[0-9A-Z]{3}([A-Z]{0,3})?$") && hasUpper){
            prefix = "0508 ";
            t = t.replaceAll("[\\(\\)]", "");
            if (lettersHyphens(t.substring(4, t.length())) != "INV"){
                t = t.replaceAll("[^A-Z0-9[()]]", "");
                return prefix + format(lettersHyphens(t.substring(4,10)));
            } else {
                return "INV";
            }
        } else if (t.matches("^\\(?(0800)\\)?\\s?\\d{3}[\\s-]?\\d{3,4}$")){
            prefix = "0800 ";
            t = t.replaceAll("[^A-Z0-9[()]]", "");
            return prefix + format(t.substring(4, (Math.min(11, t.length()))));
        } else if (t.matches("^\\(?(0800)\\)?\\s?[0-9A-Z]{3}[\\s-]?[0-9A-Z]{3,4}([A-Z]{0,2})?$") && hasUpper){
            prefix = "0800 ";
            t = t.replaceAll("[\\(\\)]", "");
            if (lettersHyphens(t.substring(4, t.length())) != "INV"){
                t = t.replaceAll("[^A-Z0-9[()]]", "");
                return prefix + format(lettersHyphens(t.substring(4,(Math.min(11, t.length())))));
            } else {
                return "INV";
            }
        } else if (t.matches("^\\(?(0900)\\)?\\s?\\d{5}$")){
            prefix = "0900 ";
            t = t.replaceAll("[^A-Z0-9[()]]", "");
            return prefix + format(t.substring(4, 9));
        } else if (t.matches("^\\(?(0900)\\)?\\s?[0-9A-Z]{5}([A-Z]{0,4})?$")){
            prefix = "0900 ";
            t = t.replaceAll("[\\(\\)]", "");
            if (lettersHyphens(t.substring(4, t.length())) != "INV"){
                t = t.replaceAll("[^A-Z0-9[()]]", "");
                return prefix + format(lettersHyphens(t.substring(4,9)));
            } else {
                return "INV";
            }

            //area codes
        } else if (t.matches("^\\(?(02)\\)?\\s?(409)[\\s-]?\\d{4}$")){
            prefix = "02 ";
            t = t.replaceAll("[^A-Z0-9[()]]", "");
            return prefix + format(t.substring(2,t.length()));
        } else if (t.matches("^\\((02)\\)\\s?\\d{3}[\\s-]?\\d{4}$")){
            return "INV";
        } else if (t.matches("^\\(?(0)[34679]\\)?\\s?((?!900|911|999)[2-9]\\d{2})[\\s-]?\\d{4}$")){
            t = t.replaceAll("[^A-Z0-9[()]]", "");
            prefix = t.substring(0,2) + " ";
            return prefix + format(t.substring(2,t.length()));

            //mobile codes
        } else if (t.matches("^\\(?(021)\\)?\\s?\\d{3}[\\s-]?\\d{3,4}$")){
            prefix = "021 ";
            t = t.replaceAll("[^A-Z0-9[()]]", "");
            return prefix + format(t.substring(3,t.length()));
        } else if (t.matches("^\\(?(021)\\)?\\s?\\d{4}[\\s-]?\\d{4}$")){
            prefix = "021 ";
            t = t.replaceAll("[^A-Z0-9[()]]", "");
            return prefix + format(t.substring(3,t.length()));
        } else if (t.matches("^\\(?((022)|(027))\\)?\\s?\\d{3}[\\s-]?\\d{4}$")){
            t = t.replaceAll("[^A-Z0-9[\\(\\)]]", "");
            prefix = t.substring(0,3) + " ";
            return prefix + format(t.substring(3,t.length()));
        } else if (t.matches("^\\(?(025)\\)?\\d{3}[\\s-]?\\d{3}$")){
            prefix = "027 4";
            t = t.replaceAll("[^A-Z0-9[()]]", "");
            return prefix + format("4" + t.substring(3,t.length()));
        } else {
            return "INV";
        }
    }
    
    /** method to replace all letters in 0508, 0800, 0900 numbers with their
        corresponding numbers
        @param number of which letters will be replaced
        @return String with converted numbers
    */
    public static String letters (String t){

        String nums = t;

        nums = nums.replaceAll("[ABC]", "2");
        nums = nums.replaceAll("[DEF]", "3");
        nums = nums.replaceAll("[GHI]", "4");
        nums = nums.replaceAll("[JKL]", "5");
        nums = nums.replaceAll("[MNO]", "6");
        nums = nums.replaceAll("[PQRS]", "7");
        nums = nums.replaceAll("[TUV]", "8");
        nums = nums.replaceAll("[WXYZ]", "9");
        return nums;
    }

    public static String lettersHyphens (String t){
        // check for hyphens between letters
        if (t.matches(".*[A-Z][\\s-][A-Z].*")){
            return "INV";
        } else {
            t = t.replaceAll("[^A-Z0-9[()]]", "");
            return letters(t);
        }
    }

    /** a method to make the numbers fit the standard format based on length.
        @param is the number to be formatted.
        @return a String of the number with correct formatting.
    */
    public static String format (String t){

        String result = t;
        if (result.length() == 6 || result.length() == 7){
            return (result.substring(0,3) + " " +
                    result.substring(3, result.length()));
        } else if (result.length() == 8){
            return (result.substring(0,4) + " " + result.substring(4,8));
        } else {
            return result;
        }
    }

    /** a method to check if any valid numbers have duplicates.
        @param t is the telephone number to be checked for duplicates
        @return a boolean to represent if there was a duplicate or not.
    */
    public static boolean dups(String t){

        if (numbers.contains(t)){
            return true;
        } numbers.add(t);
        return false;
    }   
}
