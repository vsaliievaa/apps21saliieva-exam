package json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private final List<JsonPair> jsonObject = new ArrayList<>();

    public JsonObject(JsonPair... jsonPairs) {
        jsonObject.addAll(Arrays.asList(jsonPairs));
    }

    @Override
    public String toJson() {
        return "{" + jsonObjectToJson() + "}";
    }

    public String jsonObjectToJson() {
        StringBuilder jsonObjectString = new StringBuilder();
        Iterator<JsonPair> jsonObjectIterator = jsonObject.iterator();

        while (jsonObjectIterator.hasNext()) {
            JsonPair currentPair = jsonObjectIterator.next();
            jsonObjectString.append(currentPair.key);
            jsonObjectString.append(": ");
            jsonObjectString.append(currentPair.value.toJson());
            if (jsonObjectIterator.hasNext()) {
                jsonObjectString.append(", ");
            }
        }

        return jsonObjectString.toString();
    }

    public void add(JsonPair jsonPair) {
        jsonObject.add(jsonPair);
    }

    public Json find(String name) {

        for (JsonPair currentPair : jsonObject) {
            if (currentPair.key.equals(name)) {
                return currentPair.value;
            }
        }
        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject projectedObject = new JsonObject();
        for (JsonPair pair : jsonObject) {
            if (contains(pair.key, names)) {
                projectedObject.add(pair);
            }
        }
        return projectedObject;
    }

    public boolean contains(String name, String ... names) {
        for (String entry: names) {
            if (entry.equals(name)) {
                return true;
            }
        }
        return false;
    }
}
