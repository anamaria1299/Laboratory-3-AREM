package ClientServer.PowServer;

import ClientServer.BasicServer;

import java.io.IOException;

public class EchoServer extends BasicServer {

    public static void main(String[] arg) throws IOException {

        initializeServer();
        TransformingData();
        close();
        System.exit(1);
    }

    private static void TransformingData() throws IOException {

        String inputLine, outputLine;
        while((inputLine = in.readLine()) != null) {
            System.out.println("Message: "+ inputLine);
            outputLine = "Answer: ";
            try {
                outputLine= inputLine.equals("Bye.") ? outputLine + inputLine: outputLine + Math.pow(Integer.parseInt(inputLine), 2);
                out.println(outputLine);
            } catch (Exception e) {
                System.err.println("The input is not valid.");
                out.println("The input is not valid.");
            }
            if(outputLine.equals("Answer: Bye.")){
                break;
            }
        }
    }
}
