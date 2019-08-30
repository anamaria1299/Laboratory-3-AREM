package URL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Read the attributes of a given URL
 */
public class URLReader {

    public static void main(String[] args) throws MalformedURLException {

        URL google = new URL("https://www.google.com:443/search?q=what+is+the+ref+of+url");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(google.openStream()));
            String inputLine = null;
            while((inputLine = reader.readLine()) != null) {
                System.out.println("Protocol: " + google.getProtocol());
                System.out.println("Authority: " + google.getAuthority());
                System.out.println("Host: " + google.getHost());
                System.out.println("Port: " + google.getPort());
                System.out.println("Path: " + google.getPath());
                System.out.println("Query: " + google.getQuery());
                System.out.println("File: " + google.getFile());
                System.out.println("Ref: " + google.getRef());
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
