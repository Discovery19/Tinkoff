package edu.hw6;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task6Test {
    @Test
    @Disabled
    @DisplayName("Проверка портов")
    void portsStatus() {
        //act
        String result = new Task6().portsStatus();
        //assert
        assertThat(true).isTrue();
        assertThat(result).isEqualTo("135 TCP EPMAP\n" +
            "137 UDP Служба имен NetBIOS\n" +
            "138 UDP Служба датаграмм NetBIOS\n" +
            "139 TCP Служба сеансов NetBIOS\n" +
            "500\n" +
            "1900\n" +
            "4500\n" +
            "5050\n" +
            "5353\n" +
            "5355\n" +
            "TCP 21 FTP\n" +
            "UDP 22 SSH\n" +
            "TCP 25 SMTP\n" +
            "TCP 80 HTTP\n" +
            "TCP 135\n" +
            "UDP 137\n" +
            "UDP 138\n" +
            "TCP 139\n" +
            "TCP 443 HTTPS\n");

    }
}
