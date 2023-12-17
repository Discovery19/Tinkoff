package edu.project3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class SampleTest {

    @Test
    @DisplayName("Тест файла логов markdown")
    void testSampleMarkDown() {
        //arrange
        String test = "--path src/main/resources/project3/test2 --format markdown";
        //act
        Analyzer.main(test.split(" "));
        //assert
        String result;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/result/result.md"));) {
            StringBuilder sb = new StringBuilder();
            while (reader.ready()) {
                sb.append(reader.readLine()).append("\n");
            }
            result = sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertThat(result).isEqualTo("#### Общая информация\n" +
            "| Метрика | Значение |\n" +
            "|:-------------:|:-------------:|\n" +
            "| Файл(-ы) | src/main/resources/project3/test2 |\n" +
            "| Начальная дата | - |\n" +
            "| Конечная дата | - |\n" +
            "| Количество запросов | 9 |\n" +
            "| Средний размер ответа | 126 |\n" +
            "#### Запрашиваемые ресурсы\n" +
            "| Ресурс | Количество |\n" +
            "|:-------------:|:-------------:|\n" +
            "| /downloads/product_2 | 5 |\n" +
            "| /downloads/product_1 | 3 |\n" +
            "| /downloads/TEST | 1 |\n" +
            "#### Коды ответа\n" +
            "| Код | Имя | Количество |\n" +
            "|:-------------:|:-------------:|:-------------:|\n" +
            "| 555 | Unknown | 9 |\n" +
            "| 304 | Redirection | 6 |\n" +
            "| 200 | Unknown | 2 |\n");
    }

    @Test
    @DisplayName("Тест файла логов adoc")
    void testSampleAdoc() {
        //arrange
        String test = "--path src/main/resources/project3/test --format adoc";
        //act
        Analyzer.main(test.split(" "));
        //assert
        String result;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/result/result.adoc"));) {
            StringBuilder sb = new StringBuilder();
            while (reader.ready()) {
                sb.append(reader.readLine()).append("\n");
            }
            result = sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertThat(result).isEqualTo("== Общая информация\n" +
            "|===\n" +
            "| Метрика| Значение\n" +
            "|Файл(-ы)|src/main/resources/project3/test\n" +
            "|Начальная дата|-\n" +
            "|Конечная дата|-\n" +
            "|Количество запросов|27\n" +
            "|Средний размер ответа|135\n" +
            "|===\n" +
            "== Запрашиваемые ресурсы\n" +
            "|===\n" +
            "| Ресурс| Количество\n" +
            "|/downloads/product_1|22\n" +
            "|/downloads/product_2|5\n" +
            "|===\n" +
            "== Коды ответа\n" +
            "|===\n" +
            "| Код| Имя| Количество\n" +
            "|404|Not Found |21\n" +
            "|304|Redirection|13\n" +
            "|555|Unknown|9\n" +
            "|===\n");
    }

    @Test
    @DisplayName("Тест файла логов markdown в промежутке времени")
    void testSampleMarkDownTime() {
        //arrange
        String test = "--path src/main/resources/project3/test --from 31-08-2023 --format markdown";
        //act
        Analyzer.main(test.split(" "));
        //assert
        String result;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/result/result.md"));) {
            StringBuilder sb = new StringBuilder();
            while (reader.ready()) {
                sb.append(reader.readLine()).append("\n");
            }
            result = sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertThat(result).isEqualTo("#### Общая информация\n" +
            "| Метрика | Значение |\n" +
            "|:-------------:|:-------------:|\n" +
            "| Файл(-ы) | src/main/resources/project3/test |\n" +
            "| Начальная дата | 2023-08-31 |\n" +
            "| Конечная дата | 2023-12-17 |\n" +
            "| Количество запросов | 27 |\n" +
            "| Средний размер ответа | 135 |\n" +
            "#### Запрашиваемые ресурсы\n" +
            "| Ресурс | Количество |\n" +
            "|:-------------:|:-------------:|\n" +
            "| /downloads/product_1 | 22 |\n" +
            "| /downloads/product_2 | 5 |\n" +
            "#### Коды ответа\n" +
            "| Код | Имя | Количество |\n" +
            "|:-------------:|:-------------:|:-------------:|\n" +
            "| 404 | Not Found  | 40 |\n" +
            "| 304 | Redirection | 20 |\n" +
            "| 555 | Unknown | 9 |\n");
    }

    @Test
    @DisplayName("Тест файла логов markdown URL")
    void testSampleMarkDownURL() {
        //arrange
        String test =
            "--path https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs --format markdown";
        //act
        Analyzer.main(test.split(" "));
        //assert
        String result;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/result/result.md"));) {
            StringBuilder sb = new StringBuilder();
            while (reader.ready()) {
                sb.append(reader.readLine()).append("\n");
            }
            result = sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertThat(result).isEqualTo("#### Общая информация\n" +
            "| Метрика | Значение |\n" +
            "|:-------------:|:-------------:|\n" +
            "| Файл(-ы) | https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs |\n" +
            "| Начальная дата | - |\n" +
            "| Конечная дата | - |\n" +
            "| Количество запросов | 10 |\n" +
            "| Средний размер ответа | 125 |\n" +
            "#### Запрашиваемые ресурсы\n" +
            "| Ресурс | Количество |\n" +
            "|:-------------:|:-------------:|\n" +
            "| /downloads/product_1 | 8 |\n" +
            "| /downloads/product_2 | 2 |\n" +
            "#### Коды ответа\n" +
            "| Код | Имя | Количество |\n" +
            "|:-------------:|:-------------:|:-------------:|\n" +
            "| 304 | Redirection | 6 |\n" +
            "| 200 | Unknown | 2 |\n" +
            "| 404 | Not Found  | 2 |\n");
    }

    @Test
    @DisplayName("Тест файла логов adoc URL")
    void testSampleAdocURL() {
        //arrange
        String test =
            "--path https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs --format adoc";
        //act
        Analyzer.main(test.split(" "));
        //assert
        String result;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/result/result.adoc"));) {
            StringBuilder sb = new StringBuilder();
            while (reader.ready()) {
                sb.append(reader.readLine()).append("\n");
            }
            result = sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertThat(result).isEqualTo("== Общая информация\n" +
            "|===\n" +
            "| Метрика| Значение\n" +
            "|Файл(-ы)|https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs\n" +
            "|Начальная дата|-\n" +
            "|Конечная дата|-\n" +
            "|Количество запросов|10\n" +
            "|Средний размер ответа|125\n" +
            "|===\n" +
            "== Запрашиваемые ресурсы\n" +
            "|===\n" +
            "| Ресурс| Количество\n" +
            "|/downloads/product_1|8\n" +
            "|/downloads/product_2|2\n" +
            "|===\n" +
            "== Коды ответа\n" +
            "|===\n" +
            "| Код| Имя| Количество\n" +
            "|404|Not Found |42\n" +
            "|304|Redirection|26\n" +
            "|555|Unknown|9\n" +
            "|===\n");
    }
}
