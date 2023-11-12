package edu.hw6;

import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import static java.net.http.HttpClient.newHttpClient;
import org.json.JSONObject;
@Slf4j
public class Task5 {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        System.out.println(Arrays.toString(hackerNewsTopStories()));
        String newsTitle = news();
        System.out.println(newsTitle);

    }
    static long[] hackerNewsTopStories() throws URISyntaxException, IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
            .uri(new URI("https://hacker-news.firebaseio.com/v0/topstories.json"))
            .GET()
            .build();
        var response = newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        var json = response.body().substring(1, response.body().length() - 1).replace(',', ' ').split(" ");
        long [] topStories = new long [json.length];
        for (int i=0;i< json.length; i++) {
            topStories[i] = Long.parseLong(json[i]);
        }
        return topStories;
    }
    static String news() throws URISyntaxException, IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
            .uri(new URI("https://hacker-news.firebaseio.com/v0/item/37570037.json"))
            .GET()
            .build();
        var response = newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        var json = response.body();
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject.getString("title");
    }
}
