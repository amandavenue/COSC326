import java.util.*;

/**
   A Class for the Arithmetic etude in Cosc326 SS 2018.
   Evaluates some numbers using + and * to try to reach a goal.
   Also has the ability to evaulate left to right or using BEDMAS.
   @author Amanda Veldman (4389944)
*/

public class Arithmetic {

    //set the global variable for goal
    public static Integer goal;
    
    public static void main (String [] args){

        Scanner sc = new Scanner(System.in);
        
        while (sc.hasNextLine()){
          
            String numbers = sc.nextLine();
            String constraints = sc.nextLine();

            ArrayList<Integer> nums = new ArrayList<Integer>();

            String[] tokens = new String[numbers.length()];
            tokens = numbers.split(" ");

            // add each number to my array list. convert char to Integer
            for (String token : tokens){
                nums.add(Integer.parseInt(token));
            }

            String[] rules = new String[2];
            rules = constraints.split(" ");

            // set the goal to the one input
            Arithmetic.goal = Integer.parseInt(rules[0]);
            //set the order to be used
            String order = rules[1];

            //print the combination that will work or impossible
            System.out.println(Combinations(nums, 0, order));
        }
    }

    /** perform the left to right or normal order tests and format the answer
       @param the list of numbers, the index (current position), the order
       @return the formatted answer
    */
    public static String Combinations(ArrayList<Integer> nums, int index, String order){

        int min = 0;
        String operands = "";

        //calculate the minimum answer possible
        for (int i = 0; i < nums.size(); i++){
            min += nums.get(i);
        }

        String answer = "";
        String result = "";

        // only try to calculate if the goal is bigger or equal to the min
        if (min <= goal){
            //Left to right order
            if (order.equals("L")){
                answer = SearchLeftToRight(nums, index, operands);
            // Normal order
            } else {
                answer = SearchNormal(nums, index, operands);
            }
            // if the answer isnt impossible, format the correct answer
            if (!answer.equals("impossible")){
                result = result + (order);
                for (int i = 0 ; i < (nums.size()); i++){
                    if (i == 0){
                        result = result + (" " + nums.get(i));
                    } else {
                        result = result + (" " + answer.charAt(i-1) + " " + nums.get(i));
                    }
                }
                return result;
            } else {
                // format the impossible answer
                return (order + " impossible");
            }
        }
        //program shouldn't reach here
        return (order + " impossible");
    }

    /** evaluate the numbers with left to right logic in a tree like way
        @param the list of numbers, the current index, the current operands
        @return the correct operands or impossible
    */
    public static String SearchLeftToRight(ArrayList<Integer> nums, int index, String operands){

        int result = nums.get(0);
        String addLeft = "";
        String multiplyRight = "";

        // evaluate the current total (running total)
        for (int i = 0; i < operands.length(); i++){
            if ((operands.length() > 1) && operands.charAt(i) == '+'){
                result += nums.get(i+1);
            } else {
                result *= nums.get(i+1);
            }
        }

        // End cases
        if (index == (nums.size()-1)){
            if (goal == result){
                //Success
                return operands;
            } else {
                //Failure
                return "impossible";
            }
        }

        //Search addition branch
        addLeft = SearchLeftToRight(nums, (index + 1), (operands + "+"));
        if (addLeft != "impossible"){
            return addLeft;
        } else {
            //Search multiplication branch
            multiplyRight = SearchLeftToRight(nums, (index + 1), (operands + "*"));
            if (multiplyRight != "impossible"){
                return multiplyRight;
            }
        }
        // Fail safe end. if it gets to here its impossible
        return "impossible";
    }

    /** evaluate the numbers with BEDMAS logic in a tree like way
        @param the list of numbers, the current index, the current operands
        @return the correct operands or impossible
    */
    public static String SearchNormal(ArrayList<Integer> nums, int index, String operands){

        int result = nums.get(0);
        int temp = 0;
        String addLeft = "";
        String multiplyRight = "";
        ArrayList<Integer> copy = new ArrayList<Integer>(nums);

        for (int i = 0; i < operands.length(); i++){
            //alter the copy of the array to account for *ed numbers
            if (operands.charAt(i) == '*'){
                temp = 0;
                temp = (copy.get(i) * copy.get(i+1));
                copy.set(i+1, temp);
                copy.set(i, 0);
            }
        }
        
        result = 0;
        //add all the altered numbers together
        for (int n = 0; n < copy.size(); n++){
            result += copy.get(n);
        }
        
        if (index == (nums.size()-1)){
            if (goal == result){
                //success case
                return operands;
            } else {
                //fail case
                return "impossible";
            }
        }

        //Search addition branch
        addLeft = SearchNormal(nums, (index + 1), (operands + "+"));
        if (addLeft != "impossible"){
            return addLeft;
        } else {
            //Search multiplication branch
            multiplyRight = SearchNormal(nums, (index + 1), (operands + "*"));
            if (multiplyRight != "impossible"){
                return multiplyRight;
            }
        }
        //fail safe. if it gets here its impossible
        return "impossible";
    }    
}
