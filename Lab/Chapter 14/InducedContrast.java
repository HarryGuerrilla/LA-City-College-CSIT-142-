import javax.swing.*;
import java.awt.*;

public class InducedContrast{
  public static void main(String[] args){
    JFrame window = new JFrame();
    window.setTitle("Induced Contrast");
    window.setSize(600,320);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container pane = window.getContentPane();
    pane.setLayout(new GridLayout(1,2));
    Illusion panel1 = new Illusion(Color.black);
    Illusion panel2 = new Illusion(Color.white);
    pane.add(panel1);
    pane.add(panel2);
    window.setVisible(true);
  }
}

class Illusion extends JPanel{
  public Illusion(Color c){
    setBackground(c);
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.setColor(Color.gray);
    g.fillRect(100,100,100,100);
  }
}
