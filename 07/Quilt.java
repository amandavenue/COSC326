package Quilt;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Quilt extends JPanel{

    public static int windowSize = 600;
    public static double defaultSize;
    public static ArrayList<Square> squares = new ArrayList<Square>();
    public static int drawingLayer = 0;
    public static double totalScale = 0.0;

    public static void main (String[] args){

        int layer = 1;
        
        JFrame frame = new JFrame("Amanda's Quilt");

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        
        frame.setSize(new Dimension(windowSize, windowSize+25));
        frame.getContentPane().add(new Quilt());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()){
            String[] input = sc.nextLine().split(" ");
			
            double scale = Double.parseDouble(input[0]);
            int r = Integer.parseInt(input[1]);
            int b = Integer.parseInt(input[2]);
            int g = Integer.parseInt(input[3]);
            Color colour = new Color(r,g,b);

            totalScale += scale;

            squares.add(new Square(scale, colour, layer));

            layer++;
        }

        squares.add(null);
        defaultSize = 600*totalScale;
        //System.out.println(totalScale);
        //System.out.println(squares);
    }

    public void paint(Graphics g){

        while (drawingLayer < squares.size()){
            drawSquare(g, 0, 300, 300);
            drawingLayer++;
        }
    }

    public void drawSquare(Graphics g, int i, int x, int y){

        double size = (squares.get(i).getScale() / totalScale) *(windowSize * 0.9);
        int width = (int) size;
        //System.out.println(width);

        if (drawingLayer == squares.get(i).getLayer()){
            g.setColor(squares.get(i).getColour());
            //System.out.println(squares.get(i).getColour());
            g.fillRect(x - width/2, y - width/2, width, width);
        }
        if (squares.get(i+1) != null){
            drawSquare(g, i+1, x - width/2, y - width/2);
            drawSquare(g, i+1, x + width/2, y - width/2);
            drawSquare(g, i+1, x - width/2, y + width/2);
            drawSquare(g, i+1, x + width/2, y + width/2);
        }
    }
}
