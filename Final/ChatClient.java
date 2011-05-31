import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient extends JFrame implements ComponentListener{
  private JTextField      messageField; // field where user types message
  private JTextArea       chatArea; // textarea where all messages are printed
  private JButton         sendMessageButton; 
  private JButton         quitButton;
  private Socket          client;
  private PrintStream     output;
  private BufferedReader  input;
  private String          host = "127.0.0.1";
  private int             port = 2000;
  private ExecutorService ex;            
  private boolean         stopReading = false;
  
  // Default width and height
  // ========================
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

    // menu
    // ==================================================
    // File menu includes settings for both port and host

    JMenu fileMenu = new JMenu("File");
    fileMenu.setMnemonic('F');
    
    JMenuItem settings = new JMenu("Settings");
    settings.setMnemonic('S');
    fileMenu.add(settings);

    JMenuItem host = new JMenuItem("Host");
    settings.add(host);
    host.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ChatClient.this.host = JOptionPane.showInputDialog(ChatClient.this, "Host", "127.0.0.1");
                ChatClient.this.stopReading = true;
                try {
                  System.out.println("Connecting to new host");
                  ChatClient.this.input.close();
                  System.out.println("closed input stream");
                  ChatClient.this.output.close();
                  System.out.println("closed output stream");
                  ChatClient.this.client.close();
                  System.out.println("closed socket");
                  System.out.println("connection....");
                  ChatClient.this.connect();                  
                }
                catch (IOException d) {
                  d.printStackTrace();
                }
            }
    });

    JMenuItem port = new JMenuItem("Port");
    settings.add(port);
    port.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ChatClient.this.port = Integer.parseInt(JOptionPane.showInputDialog(ChatClient.this, "Port", "2000"));
                try {
                  System.out.println("Connecting to new host");                  
                  ChatClient.this.input.close();
                  System.out.println("closed input stream");
                  ChatClient.this.output.close();
                  System.out.println("closed output stream");
                  ChatClient.this.client.close();
                  System.out.println("closed socket");
                  System.out.println("connection....");
                  ChatClient.this.connect();
                }
                catch (IOException d) {
                  d.printStackTrace();
                }
            }
    });
    
    JMenuBar bar = new JMenuBar();
    setJMenuBar(bar);
    bar.add(fileMenu);


    // The UI consists of two main areas, one that will house the chat area, and 
    // message input, and another to house the buttons.  Create two boxes to 
    // represent the two sections.
    Box textFieldBox = Box.createVerticalBox();
    Box buttonBox = Box.createVerticalBox();
    
    // create chat area
    chatArea = new JTextArea();
    chatArea.setColumns(50);
    chatArea.setRows(100);
    chatArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(chatArea, 
                                             JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                                             JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    textFieldBox.add(scrollPane);
    
    // create text input area
    messageField = new JTextField(50);
    textFieldBox.add( messageField );
    
    // send message button
    sendMessageButton = new JButton("Send");
    sendMessageButton.addActionListener(new SendListener());
    buttonBox.add(sendMessageButton);

    // quit button
    quitButton = new JButton("Quit");
    quitButton.addActionListener(
      new ActionListener(){
        public void actionPerformed(ActionEvent e){
          try {
            output.close();
            input.close();
            client.close();
            System.exit(0);
          } catch (IOException ioException) {
            ioException.printStackTrace();
          } finally {
            ex.shutdown();
          }
        }
      }
    );
    buttonBox.add(quitButton);

    add(textFieldBox, BorderLayout.CENTER);
    add(buttonBox, BorderLayout.EAST);   

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //    setVisible(true);
  }

  public void launchFrame(){
    ChatClient.this.setVisible(true);
    ChatClient.this.connect();
  }

  public void connect(){
    //eastablish the socket connection
    try {
      client = new Socket(host, port);
      if (client == null) {
        throw new IOException();
      }

      //create the streams from the socket connection
      output = new PrintStream(client.getOutputStream());
      input = new BufferedReader(new InputStreamReader(client.getInputStream()));

      messageField.addActionListener(new SendListener());
      messageField.requestFocus();

      //make a thread and start it with an instance of RemoteReader
      ExecutorService ex = Executors.newCachedThreadPool();
      ex.execute(new RemoteReader());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
   
  public class SendListener implements ActionListener{
    public void actionPerformed(ActionEvent e)
    {
      //get info from text field
      //send it to the server
      String msg = "\n" + messageField.getText();
      output.println(msg);
      //      chatArea.append(msg);
      output.flush();

      //clear the text field
      messageField.setText("");
    }
  }
    
  private class RemoteReader implements Runnable{
    public void run(){
      stopReading = false;
      while (!stopReading) {
        //do stuff here to continously read from the server using the input stream
        try {
          String msg = input.readLine();
          //put the received text into the text area
          chatArea.append(msg);
          chatArea.append(System.getProperty("line.separator"));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  // resize window to minimum size
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
    chatWindow.launchFrame();
  }
}
