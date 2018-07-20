package services;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
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

}
