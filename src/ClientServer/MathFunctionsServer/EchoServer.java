package ClientServer.MathFunctionsServer;

import ClientServer.BasicServer;

import java.io.IOException;

public class EchoServer extends BasicServer {

    private static String function = "fun:cos";

    public static void main(String[] args) throws IOException {

        initializeServer();
        transformingData();
        close();
    }

    public static void transformingData() throws IOException {

        String inputLine;
        while((inputLine = in.readLine()) != null) {
            System.out.println("Message: "+ inputLine);
            validateTermination(inputLine);
            if(validatingInput(inputLine)) {
                Double number = Double.parseDouble(inputLine);
                if(function.contains("cos")) out.println("Cos: " + Math.cos(Math.toRadians(number)));
                else if(function.contains("sin")) out.println("Sin: " + Math.sin(Math.toRadians(number)));
                else if(function.contains("tan")) out.println("Tan: " + Math.tan(Math.toRadians(number)));
                else out.printf("Function %s is not allowed\n",function);
            }
        }
    }

    private static void validateTermination(String  inputLine) {

        if(inputLine.equals("Bye.")) {

            out.println(inputLine);
            System.exit(1);
        }
    }

    private static Boolean validatingInput(String inputLine) {

        try {
            if(inputLine.contains("fun:")) {
                function = inputLine;
                out.println("Changing function to "+ inputLine);
                return false;
            }
            Double.parseDouble(inputLine);
            return true;
        } catch (Exception e) {
            System.err.println("The input is not valid.");
            out.println("The input is not valid.");
            return false;
        }
    }
}
