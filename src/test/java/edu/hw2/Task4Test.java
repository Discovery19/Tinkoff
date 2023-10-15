package edu.hw2;

import edu.hw2.Task4.Methods;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task4Test {
    @Test
    @DisplayName("Тест на получение информации о методе callingInfo")
    void infoCalling(){
        var call = Methods.callingInfo();
        assertThat(call.className()).isEqualTo(this.getClass().getName());
        assertThat(call.methodName()).isEqualTo("infoCalling");
    }
}
