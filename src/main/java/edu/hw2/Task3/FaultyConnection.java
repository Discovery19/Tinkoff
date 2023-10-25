package edu.hw2.Task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection extends ConnectionException implements Connection {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) {
        throw new ConnectionException();
    }

    @Override
    public void close() {
        LOGGER.info("Close Connection");
    }

}
