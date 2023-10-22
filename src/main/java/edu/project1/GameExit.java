package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public sealed interface GameExit permits GameLogic {
    Logger LOGGER = LogManager.getLogger();

    boolean EXIT = false;

    default boolean win() {
        LOGGER.error("You WIN!");
        return EXIT;
    }

    default boolean defeat() {
        LOGGER.error("You LOST!");
        return EXIT;
    }

    default boolean error() {
        LOGGER.error("Неправильно задано слово");
        return EXIT;
    }
}
