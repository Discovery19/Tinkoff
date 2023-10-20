package edu.hw2.Task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private static final Logger LOGGER = LogManager.getLogger();
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() throws Exception {
        tryExecute("apt update && apt upgrade -y");
    }

    private void tryExecute(String command) throws Exception {
        int n = maxAttempts;
        Connection connection;
        boolean checkComandBegin = false;
        while (n >= 0) {
            connection = manager.getConnection();
            try {
                connection.execute(command);
                checkComandBegin = true;
                connection.close();
                break;
            } catch (Exception e) {
                LOGGER.warn(e.initCause(e.getCause()));
            }
            connection.close();
            n--;
        }
        if (!checkComandBegin) {
            Exception e = new RuntimeException();
            LOGGER.warn(e.initCause(e.getCause()));
        }
    }
}
