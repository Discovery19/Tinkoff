package edu.hw2.Task3;

public class DefaultConnectionManager implements ConnectionManager {
    private static final int RANDOM_BASE = 10;

    @Override
    public Connection getConnection() {
        int k = (int) (Math.random() * RANDOM_BASE);
        return k <= RANDOM_BASE / 2 ? new FaultyConnection() : new StableConnection();
    }
}
