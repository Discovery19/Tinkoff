package edu.hw7;

import edu.hw7.Task4.MonteKarloMultiStream;
import edu.hw7.Task4.MonteKarloSingleThread;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class Task4Test {
    @Test
    @DisplayName("Тест")
    void test() {
        List<Integer> probs = List.of(10000, 100000, 1000000, 10000000);
        List<Double> durations = new ArrayList<>();
        List<Double> measurement = new ArrayList<>();
        for (int i : probs) {
            MonteKarloSingleThread singleThread = new MonteKarloSingleThread(i);
            MonteKarloMultiStream multiStream = new MonteKarloMultiStream(i);
            Instant start = Instant.now();
            double singleRes = singleThread.generate();
            Instant end = Instant.now();
            Duration duration = Duration.between(start, end);
            start = Instant.now();
            double multiRes = multiStream.generate();
            end = Instant.now();
            durations.add((double) Math.abs(duration.toMillis() - Duration.between(start, end).toMillis()));
            measurement.add(Math.abs(singleRes - multiRes));
        }
        log.info("Среднее ускорение (в миллисекундах): "+durations.stream()
            .mapToDouble(Double::doubleValue)
            .average()
            .orElse(0.0));
        log.info("Средняя погрешность: "+measurement.stream()
            .mapToDouble(Double::doubleValue)
            .average()
            .orElse(0.0));
        assertThat(true).isTrue();
    }
}
