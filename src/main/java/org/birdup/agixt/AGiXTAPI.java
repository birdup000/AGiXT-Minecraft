package org.birdup.agixt;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AGiXTAPI {

    private final String baseUri;
    private final String apiKey;

    public AGiXTAPI(String baseUri, String apiKey) {
        this.baseUri = baseUri;
        this.apiKey = apiKey;
    }

    private String sendGetRequest(String endpoint) throws Exception {
        URL url = new URL(baseUri + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        if (!apiKey.isEmpty()) {
            conn.setRequestProperty("Authorization", "Bearer " + apiKey);
        }
        conn.setRequestProperty("Content-Type", "application/json");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        conn.disconnect();

        return content.toString();
    }

    public JsonArray getProviders() throws Exception {
        String response = sendGetRequest("/api/provider");
        JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();
        return jsonResponse.getAsJsonArray("providers");
    }
}
