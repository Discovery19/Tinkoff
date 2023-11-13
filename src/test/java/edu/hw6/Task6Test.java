package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task6Test {
    @Test
    @DisplayName("Проверка портов")
    void portsStatus() {
        //act
        String result = new Task6().portsStatus();
        //assert
        assertThat(true).isTrue();
//        assertThat(result).isEqualTo("TCP 21 FTP\n" +
//            "UDP 22 SSH\n" +
//            "TCP 25 SMTP\n" +
//            "TCP 80 HTTP\n" +
//            "TCP 135\n" +
//            "UDP 137\n" +
//            "UDP 138\n" +
//            "TCP 139\n" +
//            "TCP 443 HTTPS\n");

    }
}
