import javax.swing.*;
import java.awt.*;

class FlagColor extends JPanel{
  public FlagColor(Color backColor){
    setBackground(backColor);
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);

    Font text = new Font("Ariel", 1, 20);
    g.setFont(text);
    g.setColor(Color.white);

    // France
    g.drawString("France", 165, 35);
    g.setColor(new Color(0,47,103));
    g.fillRect(50,50,100,200);
    g.setColor(Color.white);
    g.fillRect(150,50,100,200);
    g.setColor(new Color(227,61,58));
    g.fillRect(250,50,100,200);

    // Mauritius
    g.setColor(Color.white);
    g.drawString("Mauritius", 500, 35);
    g.setColor(new Color(204, 0, 0));
    g.fillRect(400,50,300,50);
    g.setColor(new Color(0, 0, 102));
    g.fillRect(400,100,300,50);
    g.setColor(new Color(255, 255, 0));
    g.fillRect(400,150,300,50);
    g.setColor(new Color(0, 153, 0));
    g.fillRect(400,200,300,50);
  }
}

class Flags{
  public static void main(String[] args){
    JFrame window = new JFrame();
    window.setTitle("Flags");
    window.setSize(750, 325);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    FlagColor panel = new FlagColor(Color.gray);
    Container pane = window.getContentPane();
    pane.add(panel);
    window.setVisible(true);
  }
}
