package ClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BasicServer {

    protected static ServerSocket serverSocket = null;
    protected static Socket clientSocket = null;
    protected static PrintWriter out;
    protected static BufferedReader in;

    public static void initializeServer() throws IOException {

        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000");
            System.exit(1);
        }

        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Server can't accept connection");
            System.exit(1);
        }
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public static void close() throws IOException{

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
