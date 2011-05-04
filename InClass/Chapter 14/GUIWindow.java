// A color panel whose background color is provided
// by the client

import javax.swing.*;
import java.awt.*;
import java.util.Random;


class ColorPanel extends JPanel{

   public ColorPanel(Color backColor){
      setBackground(backColor);
   }
}

//Display random colors in a grid
// whose dimensions are input by the user


public class GUIWindow{

   public static void main(String[] args){
      JFrame theGUI = new JFrame();
      theGUI.setTitle("GUI Example");
      String inputStr = JOptionPane.showInputDialog("Number of rows", "5");
      if (inputStr == null) return;
      int rows = Integer.parseInt(inputStr);
      inputStr = JOptionPane.showInputDialog("Number of columns", "5");
      if (inputStr == null) return;
      int cols = Integer.parseInt(inputStr);
      theGUI.setSize(cols * 50, rows * 50);
      theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Container pane = theGUI.getContentPane();
      pane.setLayout(new GridLayout(rows, cols));
      Random gen = new Random();
      for (int i = 1; i < rows * cols; i++){
         int red = gen.nextInt(256);
         int green = gen.nextInt(256);
         int blue = gen.nextInt(256);
         Color backColor = new Color(red, green, blue);
         ColorPanel panel = new ColorPanel(backColor);
         pane.add(panel);
      }
      theGUI.setVisible(true);
   }
}
