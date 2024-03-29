package ClientServer.HTTPServer;

import sun.plugin2.gluegen.runtime.BufferFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {

    private static ServerSocket serverSocket = null;
    private static boolean next = true;
    private static Socket clientSocket = null;

    public static void main(String[] args) {

        try {
            serverSocket = new ServerSocket(8080);
            while(next) {
                System.out.println("Ready to receive on port 8080");
                clientSocket = serverSocket.accept();
                ResolvingRequest(clientSocket.getOutputStream());
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void ResolvingRequest(OutputStream outputStream) throws IOException {
        String path = getPath(clientSocket.getInputStream());
        takingResources(path, outputStream);
    }

    private static String  getPath(InputStream inputStream) throws IOException {

        inputStream.mark(0);
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String inputLine = null ;
        while((inputLine=in.readLine())!=null) {
            if (!in.ready()) break;
            if (inputLine.contains("GET")) {
                System.out.println(inputLine);
                String [] get = inputLine.split(" ");
                return get[1];
            }
        }
        throw new IOException("Impossible to get url path");
    }

    private static void takingResources(String path, OutputStream outputStream) throws IOException {

        if(path.equals("/dog.html")) {
            getDog(outputStream);
        } else if (path.equals("/image.png")) {
            getImage(outputStream);
        } else {
            notFound(outputStream);
        }
    }

    private static void getDog(OutputStream outputStream) {

        PrintWriter response = new PrintWriter(outputStream, true);
        response.println("HTTP/1.1 200 OK");
        initializeHTML(response);
        response.println("<title>Dog</title>\r\n");
        response.println("</head>\r\n");
        response.println("<body>\r\n");
        response.println("This is a html page with a dog image");
        response.println("<img src=\"data:image/jpeg;base64,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\" />\r");
        response.println("</body>\r\n");
        response.println("</html>\r\n");
        response.flush();
        response.close();
    }

    private static void getImage(OutputStream outputStream) throws IOException {

        PrintWriter response = new PrintWriter(outputStream, true);
        response.println("HTTP/1.1 200 OK");
        response.println("Content-Type: image/png\r\n");
        BufferedImage image= ImageIO.read(new File(System.getProperty("user.dir"),"dog.png"));
        ImageIO.write(image, "png", outputStream);
    }

    private static void notFound(OutputStream outputStream) {

        PrintWriter response = new PrintWriter(outputStream, true);
        response.println("HTTP/1.1 404 Not Found");
        initializeHTML(response);
        response.println("<title>Not FOUND</title>\r\n");
        response.println("</head>\r\n");
        response.println("<body>\r\n");
        response.println("Page not found");
        response.println("</body>\r\n");
        response.println("</html>\r\n");
        response.flush();
        response.close();
    }

    private static void initializeHTML(PrintWriter response) {

        response.println("Content-Type: text/html\r\n");
        response.println("<!DOCTYPE html>\r\n");
        response.println("<html lang=\"en\">\r\n");
        response.println("<head>\r\n");
        response.println("<meta charset=\"UTF-8\">\r\n");
    }
}
