package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SampleTest {
    @Test
    @DisplayName("Игра виселица со словом - hello (победа)")
    void gameStandardFile() throws IOException {
        Game game = new Game();
        GameLogic gameLogic =
            game.startForTest(new DictionaryFromFile().setDictionary("src/test/java/edu/project1/resourses/dictionarySandart"));
        gameLogic.getGuess("h");
        assertThat(gameLogic.getGameStatus()).isTrue();
        gameLogic.getGuess("e");
        gameLogic.getGuess("l");
        gameLogic.getGuess("o");
        assertThat(gameLogic.getGameStatus()).isFalse();
    }
    @Test
    @DisplayName("Игра виселица со словом - hello (поражение)")
    void gameStandardFileFalse() throws IOException {
        Game game = new Game();
        GameLogic gameLogic =
            game.startForTest(new DictionaryFromFile().setDictionary("src/test/java/edu/project1/resourses/dictionarySandart"));
        gameLogic.getGuess("q");
        assertThat(gameLogic.getGameStatus()).isTrue();
        gameLogic.getGuess("e");
        gameLogic.getGuess("w");
        gameLogic.getGuess("r");
        gameLogic.getGuess("a");
        gameLogic.getGuess("x");
        assertThat(gameLogic.getGameStatus()).isFalse();
    }
    @Test
    @DisplayName("Игра виселица с пустым словарем")
    void gameEmptyFile() throws IOException {
        Game game = new Game();
        GameLogic gameLogic =
            game.startForTest(new DictionaryFromFile().setDictionary("src/test/java/edu/project1/resourses/dictionaryEmpty"));
        assertThat(gameLogic.getGameStatus()).isFalse();
    }
    @Test
    @DisplayName("Игра виселица со словом - hello (победа)")
    void gameStandardConsole() throws IOException {
        Game game = new Game();
        GameLogic gameLogic =
            game.startForTest(new DictionaryStandart().setDictionary("hello"));
        gameLogic.getGuess("h");
        assertThat(gameLogic.getGameStatus()).isTrue();
        gameLogic.getGuess("e");
        gameLogic.getGuess("l");
        gameLogic.getGuess("o");
        assertThat(gameLogic.getGameStatus()).isFalse();
    }
}
