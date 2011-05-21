import java.net.*;
import java.io.*;

public class SimpleServer {
  public static void main(String args[]) {
    ServerSocket s = null;

    // Register your service on port 5432
    try {
      s = new ServerSocket(5432);
    } catch (IOException e) {
      // ignore
    }

   // Run the listen/accept loop forever
    while (true) {
      try {
        // Wait here and listen for a connection
        Socket s1 = s.accept();

        // Get output stream associated with the socket
        OutputStream s1out = s1.getOutputStream();
        DataOutputStream dos = new DataOutputStream(s1out);

        // Send your string!
       dos.writeUTF("Hello Net World!");
        dos.writeChars("Hello Net World!");

        // Close the connection, but not the server socket
        dos.close();
        s1.close();
      } catch (IOException e) {
        // ignore
      }
    }
  }
}

/*
import java.net.*;
import java.io.*;

public class SimpleClient {
  public static void main(String args[]) {
    try {
      // Open your connection to a server, at port 5432
      // localhost used here
      Socket s1 = new Socket("127.0.0.1", 5432);

      // Get an input stream from the socket
      InputStream is = s1.getInputStream();
      // Decorate it with a "data" input stream
      DataInputStream dis = new DataInputStream(is);

      // Read the input and print it to the screen
      System.out.println(dis.readUTF());

      // When done, just close the steam and connection
      dis.close();
      s1.close();
    } catch (ConnectException connExc) {
      System.err.println("Could not connect to the server.");
    } catch (IOException e) {
      // ignore
    }
  }
}
*/
