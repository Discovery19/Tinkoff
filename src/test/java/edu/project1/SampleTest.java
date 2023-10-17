package edu.project1;

import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SampleTest {
    @Test
    @DisplayName("Игра виселица со словом - hello (победа)")
    void gameStandardFile() throws IOException {
        //arrange/act
        Game game = new Game();
        GameLogic gameLogic =
            game.startForTest(new DictionaryFromFile().setDictionary(
                "src/test/java/edu/project1/resourses/dictionarySandart"));
        gameLogic.getGuess("h");
        gameLogic.getGuess("e");
        gameLogic.getGuess("l");
        gameLogic.getGuess("o");
        //assert
        assertThat(gameLogic.getGameStatus()).isFalse();

    }

    @Test
    @DisplayName("Игра виселица со словом - hello (поражение)")
    void gameStandardFileFalse() throws IOException {
        //arrange/act
        Game game = new Game();
        GameLogic gameLogic =
            game.startForTest(new DictionaryFromFile().setDictionary(
                "src/test/java/edu/project1/resourses/dictionarySandart"));
        gameLogic.getGuess("q");
        gameLogic.getGuess("e");
        gameLogic.getGuess("w");
        gameLogic.getGuess("r");
        gameLogic.getGuess("a");
        gameLogic.getGuess("x");
        //assert
        assertThat(gameLogic.getGameStatus()).isFalse();
    }

    @Test
    @DisplayName("Игра виселица с пустым словарем")
    void gameEmptyFile() throws IOException {
        //arrange/act
        Game game = new Game();
        GameLogic gameLogic =
            game.startForTest(new DictionaryFromFile().setDictionary(
                "src/test/java/edu/project1/resourses/dictionaryEmpty"));
        //assert
        assertThat(gameLogic.getGameStatus()).isFalse();
    }

    @Test
    @DisplayName("Игра виселица со словом - hello (победа) словарь не из файла")
    void gameStandardConsole() {
        //arrange/act
        Game game = new Game();
        GameLogic gameLogic =
            game.startForTest(new DictionaryStandart().setDictionary("hello"));
        gameLogic.getGuess("h");
        assertThat(gameLogic.getGameStatus()).isTrue();
        gameLogic.getGuess("e");
        gameLogic.getGuess("l");
        gameLogic.getGuess("o");
        //assert
        assertThat(gameLogic.getGameStatus()).isFalse();
    }
}
