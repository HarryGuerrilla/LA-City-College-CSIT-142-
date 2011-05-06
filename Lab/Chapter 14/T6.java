// Tasks T6.
// --------------------------
// Represents a circle

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

 class Circle{

   private int centerX, centerY, radius;
   private Color color;
   private int direction, velocity;
   private boolean filled;

   public Circle(int x, int y, int r, Color c){
      centerX = x;
      centerY = y;
      radius = r;
      color = c;
      direction = 0;
      velocity = 0;
      filled = false;
   }

   public void draw(Graphics g){
      Color oldColor = g.getColor();
      g.setColor(color);
      // Translates circle's center to rectangle's origin for drawing.
      if (filled)
         g.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
      else
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

   public int getRadius(){
      return radius;
   }

   public int getX(){
      return centerX;
   }

   public int getY(){
      return centerY;
   }

   public void setVelocity(int v){
      velocity = v;
   }  

   public void setDirection(int d){
      direction = d % 360;
   }

   public void turn(int degrees){
      direction = (direction + degrees) % 360;
   }

   // Moves the circle in the current direction using its
   // current velocity
   public void move(){
      move((int)(velocity * Math.cos(Math.toRadians(direction))),
           (int)(velocity * Math.sin(Math.toRadians(direction))));
   }

   public void setFilled(boolean b){
      filled = b;
   }

}

// A filled circle moves back and forth
// across the panel, appearing to bounce off its edges


class ColorPanel extends JPanel{

   private Circle circle;
   private javax.swing.Timer timer;
 
   public ColorPanel(Color backColor, int width, int height){
      setBackground(backColor);
      setPreferredSize(new Dimension(width, height));
      // Circle with center point (25, 100) and radius 25
      circle = new Circle(25, height / 2, 25, Color.red);
      circle.setFilled(true);
      // Aim due west to hit left boundary first
      circle.setDirection(180);
      // Move 5 pixels per unit of time
      circle.setVelocity(5);
      // Move every 5 milliseconds 
      timer = new javax.swing.Timer(5, new MoveListener());
      addMouseListener(new PauseListener());
      timer.start();
   }
 
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      circle.fill(g);      
   }

   private class MoveListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         int x = circle.getX();
         int radius = circle.getRadius();
         int width = getWidth();
         // Check for boundaries and reverse direction
         // if necessary
         if (x - radius <= 0 || x + radius >= width)
            circle.turn(180);
         circle.move();
         repaint();
      }
   }

  private class PauseListener extends MouseAdapter{
    boolean pauseCircle = true;
    public void mousePressed(MouseEvent e){
      if (pauseCircle) {
        timer.stop();
        pauseCircle = false;
      } else {
        timer.start();
        pauseCircle = true;
      }
      repaint();
    }
  }
}

// A frame with an empty panel

public class T6{

   public static void main(String[] args){
      JFrame theGUI = new JFrame();
      theGUI.setTitle("GUI Program");
      theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      ColorPanel panel = new ColorPanel(Color.white, 300, 200);
      Container pane = theGUI.getContentPane();
      pane.add(panel);
      theGUI.pack();
      theGUI.setVisible(true);
   }
}
