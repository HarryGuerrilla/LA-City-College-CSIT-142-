import javax.swing.*;
import java.awt.*;

public class MullerLyer{
  public static void main(String[] args){
    JFrame window = new JFrame();
    window.setTitle("Muller-Lyer");
    window.setSize(300, 300);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new Illusion();
    panel.setBackground(new Color(215,215,215));
    Container pane = window.getContentPane();
    pane.add(panel);
    window.setVisible(true);
  }
}


class Illusion extends JPanel{
  public void paintComponent(Graphics g1){
    super.paintComponent(g1);

    Graphics2D g = (Graphics2D) g1;

    g.setColor(new Color(45,45,45));
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
    Stroke line = new BasicStroke(5.0f);
    g.setStroke(line);

    // long line
    g.drawLine(75,70, 75,200);
    g.drawLine(35,30,75,70);
    g.drawLine(115,30,75,70);
    g.drawLine(35,240,75,200);
    g.drawLine(115,240,75,200);

    // short line
    g.drawLine(220,70, 220,200);
    g.drawLine(180,110,220,70);
    g.drawLine(260,110,220,70);
    g.drawLine(180,160,220,200);
    g.drawLine(260,160,220,200);
  }
}
