package Datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatagramTimeClient implements Runnable {
    DatagramSocket socket;
    String wait;
    public static void main(String[] args) throws SocketException {
        DatagramTimeClient datagram= new DatagramTimeClient();
        datagram.run();

    }
    public DatagramTimeClient() throws SocketException{
        socket = new DatagramSocket();
        socket.setSoTimeout(6000);
    }

    @Override
    public void run() {
        while(true) {
            try {
                byte[] buf = new byte[256];
                InetAddress address = InetAddress.getByName("127.0.0.1");
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
                socket.send(packet);
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                wait= received;
                System.out.println("Date: " + received);
            } catch (SocketTimeoutException ex) {
                System.out.println("El servidor no esta disponible la ultima fecha fue: "+wait);
            } catch (UnknownHostException ex) {
                Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}