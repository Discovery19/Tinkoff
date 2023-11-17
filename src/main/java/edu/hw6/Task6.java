package edu.hw6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Map;
import java.util.TreeMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Task6 {
    private static final TreeMap<Integer, String[]> PORTS = new TreeMap<>();

    static {
        //CHECKSTYLE:OFF: checkstyle:magicnumber
        PORTS.put(21, new String[] {"TCP", "FTP"});
        PORTS.put(22, new String[] {"UDP", "SSH"});
        PORTS.put(25, new String[] {"TCP", "SMTP"});
        PORTS.put(80, new String[] {"TCP", "HTTP"});
        PORTS.put(135, new String[] {"TCP", "EPMAP"});
        PORTS.put(137, new String[] {"UDP", "Служба имен NetBIOS"});
        PORTS.put(138, new String[] {"UDP", "Служба датаграмм NetBIOS"});
        PORTS.put(139, new String[] {"TCP", "Служба сеансов NetBIOS"});
        PORTS.put(443, new String[] {"TCP", "HTTPS"});
    }

    public String portsStatus() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, String[]> entry : PORTS.entrySet()) {
            if (entry.getValue()[0].equals("TCP")) {
                try (ServerSocket serverSocket = new ServerSocket(entry.getKey())) {
                    sb.append(entry.getValue()[0]).append(" ").append(entry.getKey()).append(" ")
                        .append(entry.getValue()[1]).append("\n");
                } catch (IOException e) {
                    sb.append(entry.getValue()[0]).append(" ").append(entry.getKey()).append("\n");
                }
            } else {
                try (DatagramSocket ignored = new DatagramSocket(entry.getKey())) {
                    sb.append(entry.getValue()[0]).append(" ").append(entry.getKey()).append(" ")
                        .append(entry.getValue()[1]).append("\n");
                } catch (IOException exception) {
                    sb.append(entry.getValue()[0]).append(" ").append(entry.getKey()).append("\n");
                }
            }
        }
        return sb.toString();
    }
}
