package sk.dzurikm.yts.constants;

import java.util.HashMap;
import java.util.Map;

public class RequestParameters {
    private HashMap<String,String> parameters;

    public RequestParameters(HashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    public RequestParameters() {
    }

    @Override
    public String toString() {
        StringBuilder params = new StringBuilder();

        if (parameters == null || parameters.size() == 0) return "";

        int i = 0;
        for(Map.Entry<String, String> entry : parameters.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (i != 0) params.append("&");
            params.append(key + "=" + value);


            // do what you have to do here
            // In your case, another loop.
            i++;
        }

        return params.toString();
    }
}
