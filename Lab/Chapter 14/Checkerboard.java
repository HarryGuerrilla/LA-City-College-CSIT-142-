import javax.swing.*;
import java.awt.*;

class Checkerboard{
  public static void main(String[] args){
    JFrame window = new JFrame();
    window.setTitle("Checkerboard");
    window.setSize(8 * 50, 8 * 50);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    Container pane = window.getContentPane();
    pane.setLayout(new GridLayout(8,8));

    boolean black = true;
    for (int i = 1; i<=8*8; i++) {
      if (black) {
        pane.add(new ColorPanel(Color.black));
        black = false;
      } else {
        pane.add(new ColorPanel(Color.red));
        black = true;
      }
      if (i%8==0) {
        black = !black;
      }
    }
    
    window.setVisible(true);
  }
}

class ColorPanel extends JPanel{
   public ColorPanel(Color backColor){
      setBackground(backColor);
   }  
}
