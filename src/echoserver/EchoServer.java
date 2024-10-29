package echoserver;

import java.net.*;
import java.io.*;

public class EchoServer {
    public static final int portNumber = 6013;  // Port number to listen on

    public static void main(String[] args) {
        try {
            //using the Git link from the lab to help
            // start lisstening on the specified prot
            ServerSocket serverSocket = new ServerSocket(portNumber);

            // runs forever, it waiting for client to connecitons
            while (true) {
                // waits for the clinet to connect in
                Socket clientSocket = serverSocket.accept();

                // getting the input and output streams 
                InputStream input = clientSocket.getInputStream();
                OutputStream output = clientSocket.getOutputStream();

                // echo each byte sent from client once stream ends it stops
                for (int byteRead = input.read(); byteRead != -1; byteRead = input.read()) {
                    output.write(byteRead);
                    output.flush();  
                }

                // it will close the client socket when done
                clientSocket.close();
                System.out.println("Client disconnected.");
            }
        } catch (IOException ioe) {
            System.out.println("caught unexpected ioexception");
        }
    }
}
