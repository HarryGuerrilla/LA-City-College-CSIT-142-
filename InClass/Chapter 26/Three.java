// Display output from three threads to text area
import javax.swing.*;

public class Three extends JFrame {
  private JTextArea jta = new JTextArea();

  public static void main(String[] args) {
    Three frame = new Three();
    frame.setTitle("Three Threads");
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }

  public Three() {
    JScrollPane jsp = new JScrollPane(jta);
    add(jsp);
    jta.setLineWrap(true);

    Thread printA = new Thread(new PrintChar('a', 100));
    Thread printB = new Thread(new PrintChar('b', 100));
    Thread  print1000 = new Thread(new PrintNum(100));

    // Start threads
    print1000.start();
    printA.start();
    printB.start();
  }

  //The thread class for printing a specified character in specified times
  class PrintChar implements Runnable {
    private char charToPrint;  //the character to print
    private int	times;  //the times to repeat

    //The thread class constructor
    public PrintChar(char c, int t) {
      charToPrint = c;
      times = t;
    }

    //override the run() method to tell the system what the thread will do
    public void run() {
      for (int i=1; i < times; i++)
        jta.append(charToPrint+"");
    }
  }

  //The thread class for printing number from 1 to n for a given n.
  class PrintNum implements Runnable {
    private int lastNum;
    public PrintNum(int i) {
      lastNum = i;
    }

    public void run() {
      for (int i=1; i <= lastNum; i++)
        jta.append(" "+i);
    }
  }
}
