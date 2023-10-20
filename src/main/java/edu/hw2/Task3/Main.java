package edu.hw2.Task3;

public class Main {
    private Main() {

    }

    private static final int MAXATTEMPTS = 10;

    public static void main(String[] args) throws Exception {

        ConnectionManager dc = new DefaultConnectionManager();
        PopularCommandExecutor cmd = new PopularCommandExecutor(dc, MAXATTEMPTS);
        cmd.updatePackages();
    }
}
