package edu.hw8.Task1;

import java.io.IOException;

public class Main {
    private Main() {

    }

    public static void main(String[] args) throws IOException {
        //CHECKSTYLE:OFF: checkstyle:MagicNumber
        String host = "localhost";
        int port = 8080;
        int serverPool = 2;
        Server server = new Server(port, serverPool);
        server.startServer();

        Client client = new Client(host, port);
        Client client1 = new Client(host, port);

        server.connectClients(client, client1);

    }
}
