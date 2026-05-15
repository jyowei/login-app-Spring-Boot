package com.example.demo.client;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FirebaseClient {

    @Value("${firebase.api-key}")
    private String apiKey;

    public boolean request(String endpoint, String email, String password) {

        try {
            String urlStr = endpoint + apiKey;

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String json = String.format(
                "{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}",
                email, password
            );

            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes("UTF-8"));
                os.flush();
            }

            return conn.getResponseCode() == HttpURLConnection.HTTP_OK;

        } catch (Exception e) {
            return false;
        }
    }
}