package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task1Test {
    @Test
    @DisplayName("Обычный Атбаш")
    void atBashStandard() {
        //arrange
        String string = "abcdef";
        //act
        String res = Task1.atBash(string);
        //assert
        assertThat(res).isEqualTo("zyxwvu");
    }

    @Test
    @DisplayName("Атбаш с заглавными буквами")
    void atBashWithBigLetters() {
        //arrange
        String string = "AbcDef";
        //act
        String res = Task1.atBash(string);
        //assert
        assertThat(res).isEqualTo("ZyxWvu");
    }

    @Test
    @DisplayName("Атбаш с символами не латинского алфавита")
    void atBashWithNoLatinSymbols() {
        //arrange
        String string = "abc123 ЧтО-то def";
        //act
        String res = Task1.atBash(string);
        //assert
        assertThat(res).isEqualTo("zyx123 ЧтО-то wvu");
    }

}
