package edu.hw2.Task3;

public class DefaultConnectionManager implements ConnectionManager {
    private static final int TEN = 10;

    @Override
    public Connection getConnection() {
        int k = (int) (Math.random() * TEN);
        return k <= TEN / 2 ? new FaultyConnection() : new StableConnection();
    }
}
