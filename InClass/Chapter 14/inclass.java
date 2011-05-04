import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class Circle{

   private int centerX, centerY, radius;
   private Color color;

   public Circle(int x, int y, int r, Color c){
      centerX = x;
      centerY = y;
      radius = r;
      color = c;
   }

   public void draw(Graphics g){
      Color oldColor = g.getColor();
      g.setColor(color);
      // Translates circle's center to rectangle's origin for drawing.
      g.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
      g.setColor(oldColor);
   }

   public void fill(Graphics g){
      Color oldColor = g.getColor();
      g.setColor(color);
      // Translates circle's center to rectangle's origin for drawing.
      g.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
      g.setColor(oldColor);
   }

   public boolean containsPoint(int x, int y){
      int xSquared = (x - centerX) * (x - centerX);
      int ySquared = (y - centerY) * (y - centerY);
      int radiusSquared = radius * radius;
      return xSquared + ySquared - radiusSquared <= 0;
   }

   public void move(int xAmount, int yAmount){
      centerX = centerX + xAmount;
      centerY = centerY + yAmount;
   }
}

// Displays a circle and a filled circle
// Allows the user to drag a circle to another position


class ColorPanel extends JPanel{

   private Circle c1, c2;
   private Circle selectedCircle;  // Used to track selected shape
   private int x, y;               // Used to track mouse coordinates  
 
   public ColorPanel(Color backColor){
      setBackground(backColor);
      c1 = new Circle(200, 100, 25, Color.red);
      c2 = new Circle(100, 100, 50, Color.blue);
      selectedCircle = null;   
      addMouseListener(new PanelListener());
      addMouseMotionListener(new PanelMotionListener());
   }
 
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      c1.fill(g);
      c2.draw(g);      
   }

   private class PanelListener extends MouseAdapter{

      public void mousePressed(MouseEvent e){
         x = e.getX();
         y = e.getY();
         if (c1.containsPoint(x, y))
            selectedCircle = c1;
         else if (c2.containsPoint(x, y))
            selectedCircle = c2; 
      }

      public void mouseReleased(MouseEvent e){
         x = e.getX();
         y = e.getY();
         selectedCircle = null;
      }        
   }

   private class PanelMotionListener extends MouseMotionAdapter{

      public void mouseDragged(MouseEvent e){
         int newX = e.getX();
         int newY = e.getY();
         int dx = newX - x;
         int dy = newY - y;
         if (selectedCircle != null)
            selectedCircle.move(dx, dy);
         x = newX;
         y = newY; 
         repaint();
      }
   } 
}

// A frame with an empty panel

public class GUIWindow{

   public static void main(String[] args){
      JFrame theGUI = new JFrame();
      theGUI.setTitle("GUI Program");
      theGUI.setSize(300, 200);
      theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      ColorPanel panel = new ColorPanel(Color.white);
      Container pane = theGUI.getContentPane();
      pane.setLayout(new GridLayout(1, 1));
      pane.add(panel);
      theGUI.setVisible(true);
   }
}
