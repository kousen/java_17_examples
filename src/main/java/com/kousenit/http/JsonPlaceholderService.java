package com.kousenit.http;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JsonPlaceholderService {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final ObjectMapper mapper = new ObjectMapper();
    private final HttpClient client = HttpClient.newHttpClient();

    public BlogPost getPost(int id) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(URI.create("%s/posts/%d".formatted(BASE_URL, id)))
                .header("Accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(response.body(), BlogPost.class);
    }

    public BlogPost submitPost(BlogPost post) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(URI.create("%s/posts".formatted(BASE_URL)))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(post)))
                .build();
        HttpResponse<String> updatedPost = client.send(request, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(updatedPost.body(), BlogPost.class);
    }

}
