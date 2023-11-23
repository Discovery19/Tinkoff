package edu.hw6;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Task5 {
    private Task5() {
    }

    public static long[] hackerNewsTopStories() throws URISyntaxException, IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
            .uri(new URI("https://hacker-news.firebaseio.com/v0/topstories.json"))
            .GET()
            .build();
        try (var client = HttpClient.newBuilder().build()) {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            var json = response.body().substring(1, response.body().length() - 1).split(",");
            long[] topStories = new long[json.length];
            for (int i = 0; i < json.length; i++) {
                topStories[i] = Long.parseLong(json[i]);
            }
            return topStories;
        }
    }

    public static String news(long id) {
        var request = HttpRequest.newBuilder()
            .uri(URI.create(String.format("https://hacker-news.firebaseio.com/v0/item/%d.json", id)))
            .GET()
            .build();
        try (HttpClient client = HttpClient.newBuilder().build()) {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonString = response.body();
            Pattern pattern = Pattern.compile("^.*\"title\":\"([^\"]*)\".*$");
            Matcher matcher = pattern.matcher(jsonString);
            if (matcher.matches()) {
                return matcher.group(1);
            }

        } catch (IOException | InterruptedException ignored) {
            log.error("Exception");
        }
        return null;
    }
}
