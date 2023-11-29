package edu.hw8.Task1;

public class Main {
    private Main() {

    }

    public static void main(String[] args) {
        Client client = new Client();
        client.connect();
        Client client1 = new Client();
        client1.connect();
        Server server = new Server();
        server.startServer();
    }
}
