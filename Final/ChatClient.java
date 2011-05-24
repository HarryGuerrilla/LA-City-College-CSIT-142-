import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.Socket;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient{
   public ChatClient() {
   //Initialize the GUI components and other data.
   
   }

   public void launchFrame(){
   //make the GUI
   //eastablish the socket connection
   //make a thread and start it with an instance of RemoteReader
   //create the streams from the socket connection
   //make gui visible
   }
   
   public class SendListener implements ActionListener{
       public void actionPerformed(ActionEvent e)
       {
          //get info from text field
          //send it to the server
          //clear the text field
       }
   }
    
  private class RemoteReader implements Runnable{
    public void run(){
      while (true) {
          //do stuff here to continously read from the server using the input stream
          //put the received text into the text area
           
      }
    }
  }

  public static void main(String args[]) {
    
    ChatFrame window = new ChatFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setVisible(true);
    // try {
    //   // Open your connection to a server, at port 5432
    //   // localhost used here
    //   Socket s1 = new Socket("127.0.0.1", 2000);

    //   // Get an input stream from the socket
    //   InputStream is = s1.getInputStream();
    //   // Decorate it with a "data" input stream
    //   DataInputStream dis = new DataInputStream(is);

    //   // Read the input and print it to the screen
    //   System.out.println(dis.readUTF());
    //   System.out.println(dis.readChar());

    //   // When done, just close the steam and connection
    //   dis.close();
    //   s1.close();
    // } catch (ConnectException connExc) {
    //   System.err.println("Could not connect to the server.");
    // } catch (IOException e) {
    //   // ignore
    // }
  }
}

class ChatFrame extends JFrame implements ComponentListener {
  private JTextField messageField;
  private JTextArea  chatArea;
  private JButton    sendMessageButton;
  private JButton    quitButton;

  static final int WIDTH = 700;
  static final int HEIGHT = 400;
  
  public ChatFrame(){
    super("Chat Window");
    setLayout(new BorderLayout());
    setSize(WIDTH,HEIGHT);
    addComponentListener(this);

    Box textFieldBox = Box.createVerticalBox();
    Box buttonBox = Box.createVerticalBox();
    
    chatArea = new JTextArea();
    chatArea.setColumns(50);
    chatArea.setRows(100);
    JScrollPane scrollPane = new JScrollPane(chatArea, 
                                             JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                                             JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    textFieldBox.add(scrollPane);
    
    messageField = new JTextField(50);
    textFieldBox.add( messageField );

    sendMessageButton = new JButton("Send");
    sendMessageButton.addActionListener(
      new ActionListener(){
        public void actionPerformed(ActionEvent e){
          //Send message
        }
      }
    );
    buttonBox.add(sendMessageButton);

    quitButton = new JButton("Quit");
    quitButton.addActionListener(
      new ActionListener(){
        public void actionPerformed(ActionEvent e){
          System.exit(0);
        }
      }
    );
    buttonBox.add(quitButton);

    add(textFieldBox, BorderLayout.CENTER);
    add(buttonBox, BorderLayout.EAST);
  }
  
  public void componentResized(ComponentEvent e){
    int width = getWidth();
    int height = getHeight();
    boolean resize = false;
    if (width<WIDTH) {
      resize = true;
      width = WIDTH;
    }
    if (height<HEIGHT) {
      resize = true;
      height = HEIGHT;
    }
    if (resize)
      setSize(width,height);
  }
  public void componentMoved(ComponentEvent e){}
  public void componentShown(ComponentEvent e){}
  public void componentHidden(ComponentEvent e){}
}
