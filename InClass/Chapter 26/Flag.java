import javax.swing.*;

import java.awt.*;
import java.applet.*;
import java.net.*;

public class Flag extends JApplet {
  public Flag() {
    URL imageURL = this.getClass().getResource("us.gif");
    Image image = new ImageIcon(imageURL).getImage();
    add(new FlagAnthemPanel(image));
  }

  public static class FlagAnthemPanel extends JPanel implements Runnable {
    private Image image;
    private Thread timer = new Thread(this);
    int x = 20;
    int y = this.getHeight();

    public FlagAnthemPanel(Image image) {
      this.image = image;
      timer.start();

      URL audioURL = this.getClass().getResource("us.mid");
      AudioClip audioClip = Applet.newAudioClip(audioURL);
      audioClip.play();

    }

    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      if (y > 0) {
        y -= 1;
      }
			else
				y = this.getHeight();

      g.drawImage(image, x, y, 60, 40, this);
    }

    public void run() {
      try {
        while (true) {
          repaint();
          Thread.sleep(400);
        }
      }
      catch (InterruptedException ex) {
      }
    }
  }

  public static void main(String[] args) {
    // Create a frame
    JFrame frame = new JFrame("Flag & Anthem");

    // Create an instance of the applet
    JApplet applet = new Flag();

    // Add the applet to the frame
    frame.add(applet, BorderLayout.CENTER);

    // Invoke applet's init method
    applet.init();

    // Display the frame
    frame.setSize(300, 200);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}
