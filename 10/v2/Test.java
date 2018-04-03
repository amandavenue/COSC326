public class Test{

    public static void main (String[] agrs){

        int red = 2;
        int green = 3;
        int blue = 1;

        if (red == green && green == blue) {
            System.out.println( "Gold");
        } else if (red != green && green != blue && blue != red){
            if ( red > green && red > blue ){
                System.out.println("Red");
            } else if ( green > red && green > blue ){
                System.out.println("Green");
            } else {
                System.out.println("Blue");
            }
        } else if (red == green || green == blue || blue == red){
            if (red ==  green){
                System.out.println("Blue");
            } else if (green == blue){
                System.out.println("Red");
            } else {
                System.out.println("Green");
            }
        } else {
            System.out.println("Inconclusive");
        }
    }
}
