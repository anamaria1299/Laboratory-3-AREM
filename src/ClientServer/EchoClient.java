package ClientServer;

import java.io.IOException;

public class EchoClient extends BasicClient {

    public static void main(String[] args) throws IOException {

        initializeClient();
        SendingData();
        close();
    }

    private static void SendingData() throws IOException {
        String userInput, serverResponse;
        while((userInput = bufferedReader.readLine()) != null) {
            out.println(userInput);
            serverResponse = in.readLine();
            System.out.println(serverResponse);
            if (serverResponse.contains("Bye.")) System.exit(1);
        }
    }

}