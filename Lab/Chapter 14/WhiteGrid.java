import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

class WhiteGrid{
  public static void main(String[] args){
    JFrame window = new JFrame();
    window.setTitle("White Grid");
    window.setSize(8*50,8*50);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container pane = window.getContentPane();
    pane.setLayout(new GridLayout(8, 8));
    for (int i = 1; i <= 8*8; i++) {
      RandColorPanel panel = new RandColorPanel();
      pane.add(panel);
    }
    window.setVisible(true);
  }
}


class RandColorPanel extends JPanel{
  public RandColorPanel(){
    setBackground(new Color(255,255,255));
    addMouseListener(new PanelListener(this));
  }
  
  private class PanelListener extends MouseAdapter{
    Random gen = new Random();
    JPanel p;

    PanelListener(RandColorPanel whitePanel){
      p = whitePanel;
    }

    public void mousePressed(MouseEvent e){
      int r = gen.nextInt(256);
      int g = gen.nextInt(256);
      int b = gen.nextInt(256);
      p.setBackground(new Color(r,g,b));
      repaint();
    }
  }
}
