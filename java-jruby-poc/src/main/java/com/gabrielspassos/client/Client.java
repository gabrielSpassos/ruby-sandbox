package com.gabrielspassos.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client {

    public long run() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/people"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .GET()
                .build();
        long startTime = System.currentTimeMillis();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;

        System.out.println("HTTP Response Status " + response.statusCode());
        System.out.println("HTTP Response Body" + response.body());

        System.out.println("Start " + startTime);
        System.out.println("End " + endTime);
        System.out.println("Final " + timeTaken + "ms");
        return timeTaken;
    }

}
