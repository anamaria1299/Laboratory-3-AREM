package URL;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Browser {

    public static void main(String[] args) {

        System.out.println("Please write a valid url:");
        Scanner scanner = new Scanner(System.in);
        try {
            URL url = new URL(scanner.next());
            try {
                FileWriter fileWriter = new FileWriter("src/resources/result.html");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                String inputLine = null;
                while ((inputLine= bufferedReader.readLine()) != null) {
                    fileWriter.write(inputLine);
                }
                fileWriter.close();
            } catch (IOException e) {
                System.err.println("There was an exception processing your request: "+ e.getMessage());
            }
        } catch (MalformedURLException e) {
            System.err.println("The given URL is not valid or has a problem");
        }
    }
}
