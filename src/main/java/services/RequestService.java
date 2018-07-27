package services;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class RequestService {

    private String key = "MW9S-E7SL-26DU-VV8V";

    public Map<String, String> generateUrlParameters(String command) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("cmd", command);
        parameters.put("json", "y");
        parameters.put("key", key);
        return parameters;
    }

    public Map<String, String> generateUrlWithParameter(String command, String param, String value) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("cmd", command);
        parameters.put("json", "y");
        parameters.put("key", key);
        parameters.put(param, value);
        return parameters;
    }

    public String getParamsStringWithExtraParam(String command, String param, String value) throws UnsupportedEncodingException {
        Map<String, String> params = generateUrlWithParameter(command, param, value);
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
    }

    public String getParamsString(String command) throws UnsupportedEncodingException {
        Map<String, String> params = generateUrlParameters(command);
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
    }

    public String getRequestContent(String fullPath) {
        String output;
        String content = "";

        try {
            URL url = new URL(fullPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed http error code " +
                        connection.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()
            ));

            while ((output = br.readLine()) != null) {
                content += output;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return content;
    }

}
