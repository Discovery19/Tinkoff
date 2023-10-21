package edu.hw2;

import edu.hw2.Task3.Connection;
import edu.hw2.Task3.DefaultConnectionManager;
import edu.hw2.Task3.FaultyConnection;
import edu.hw2.Task3.FaultyConnectionManager;
import edu.hw2.Task3.StableConnection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task3Test {
    @Test
    @DisplayName("Default Connection Test")
    void testDefaultConnection() {
        var connectionManager = new DefaultConnectionManager();
        Connection connection = connectionManager.getConnection();
        assertTrue(connection instanceof StableConnection || connection instanceof FaultyConnection);
    }

    @Test
    @DisplayName("Faulty Connection test")
    void testFaultyConnection() {
        var connectionManager = new FaultyConnectionManager();
        assertTrue(connectionManager.getConnection() instanceof FaultyConnection);
    }
}
