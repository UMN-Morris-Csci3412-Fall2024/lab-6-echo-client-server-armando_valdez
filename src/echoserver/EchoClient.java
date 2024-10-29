package echoserver;

import java.net.*;
import java.io.*;

public class EchoClient {
    //used link from lab to help
    //match the server
    public static final int portNumber = 6013; 
    public static void main(String[] args) {
        String server;

        // Use "127.0.0.1", ie, localhost, if no server is specified.
        if (args.length == 0) {
            server = "127.0.0.1";
        } else {
            server = args[0];
        }

        try {
            //connect to the server
            Socket socket = new Socket(server, portNumber);

            //Get the input stream so we can read from that socekt
            InputStream input = socket.getInputStream();
            // now get output streams from the socket
            OutputStream output = socket.getOutputStream();

            // it will read from System.in and tell the server
            // it will send each byte to the server
            int byteRead;
            while ((byteRead = System.in.read()) != -1) {
                output.write(byteRead);  
                output.flush();
            }

            // tells server no more data can't be sent
            socket.shutdownOutput();

            // it will read the echoed data from the server
            while ((byteRead = input.read()) != -1) {
                System.out.write(byteRead);  
                System.out.flush();
            }

            // close the socket when were done reading from it
            socket.close();
        // provide some minimal error handling
        } catch (ConnectException ce) {
            System.out.println("Unable to connect to " + server);
            System.out.println("Ensure the server is running.");
        } catch (IOException ioe) {
            System.out.println("We caught an unexpected exception");
            System.err.println(ioe);
    }
    }
}
