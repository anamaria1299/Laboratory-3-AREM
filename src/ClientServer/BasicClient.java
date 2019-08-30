package ClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class BasicClient {

    protected static Socket echoSocket = null;
    protected static PrintWriter out = null;
    protected static BufferedReader in = null;
    protected static BufferedReader bufferedReader;

    public static void initializeClient() {


        try {
            echoSocket = new Socket("127.0.0.1", 35000);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Unknown host exception");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Could not get I/O for the connection.");
            System.exit(1);
        }
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void close() throws IOException {

        out.close();
        in.close();
        bufferedReader.close();
        echoSocket.close();
    }
}
