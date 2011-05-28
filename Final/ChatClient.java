import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient extends JFrame implements ComponentListener{
  private JTextField     messageField; // field where user types message
  private JTextArea      chatArea; // textarea where all messages are printed
  private JButton        sendMessageButton; 
  private JButton        quitButton;
  private Socket         client;
  private PrintStream    output;
  private BufferedReader input;
  
  // Default width and height
  // if the user decreases the size of the window, it will resize to the 
  // default.
  static final int WIDTH = 700;
  static final int HEIGHT = 400;

  public ChatClient() {
    //Initialize the GUI components and other data.
  
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
    sendMessageButton.addActionListener(new SendListener());
    buttonBox.add(sendMessageButton);

    quitButton = new JButton("Quit");
    quitButton.addActionListener(
      new ActionListener(){
        public void actionPerformed(ActionEvent e){
          try {
            output.close();
            client.close();
            System.exit(0);
          } catch (IOException ioException) {
            ioException.printStackTrace();
          }
        }
      }
    );
    buttonBox.add(quitButton);

    add(textFieldBox, BorderLayout.CENTER);
    add(buttonBox, BorderLayout.EAST);   

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public void connect(){
    //eastablish the socket connection
    try {
      client = new Socket("127.0.0.1", 2000);
      if (client == null) {
        throw new IOException();
      }

      //make a thread and start it with an instance of RemoteReader

      //create the streams from the socket connection
      output = new PrintStream(client.getOutputStream());

      input = new BufferedReader(new InputStreamReader(client.getInputStream()));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
   
  public class SendListener implements ActionListener{
    public void actionPerformed(ActionEvent e)
    {
      //get info from text field
      //send it to the server
      String msg = messageField.getText() + "\n";
      output.println(msg);
      chatArea.append(msg);
      output.flush();

      //clear the text field
      messageField.setText("");
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

  // Resize window to minimum size
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

  public static void main(String args[]) {
    ChatClient chatWindow = new ChatClient();
    chatWindow.connect();

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
