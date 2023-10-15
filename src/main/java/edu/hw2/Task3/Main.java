package edu.hw2.Task3;

public class Main {
    private Main() {

    }

    private static final int MAXATTEMPTS = 10;

    public static void main(String[] args) throws Exception {

        ConnectionManager dc = new DefaultConnectionManager();
        ConnectionManager fc = new FaultyConnectionManager();
        PopularCommandExecutor cmd = new PopularCommandExecutor(dc, MAXATTEMPTS);
        cmd.updatePackages();
//        PopularCommandExecutor pce = new PopularCommandExecutor(fc, MAXATTEMPTS);
//        pce.updatePackages();
    }
}
