package edu.hw6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Task6 extends java.net.Socket{
    public static void main(String[] args) {
        System.out.println(portsStatus(ports));
    }
    private static final int[] ports = {135, 137, 138, 139, 445, 843, 1900, 3702, 5040, 5050, 5353, 5355, 5939, 6463, 6942, 17500, 17500, 17600, 27017, 42420};
    public static String portsStatus(int [] ports) {
        StringBuilder sb = new StringBuilder();
        for (int port : ports) {
            boolean isAvailable;
            try {
                var socket= new ServerSocket(port);
                sb.append(socket.getInetAddress()).append(" ").append(port).append(" ").append("available").append("\n");
                socket.close();
            } catch (IOException e) {
                sb.append(e.getMessage()).append(" ").append(port).append(" ").append("unavailable").append("\n");

            }
        }
        return sb.toString();
    }
}
