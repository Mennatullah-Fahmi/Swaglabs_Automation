package utils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class DataDriven {
    private JsonObject root;

    // Constructor
    // Automatically loads and parses the JSON file
    public DataDriven() {
        loadJson();
    }

    // Reads testData.json and converts it into a JsonObject
    private void loadJson() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("testData.json")) {
            if (is == null) {
                throw new RuntimeException("testData.json not found in resources");
            }
            Reader reader = new InputStreamReader(is);
            Gson gson = new Gson();
            root = gson.fromJson(reader, JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load testData.json", e);
        }
    }

    // Returns valid username from JSON
    public String getValidUsername() {
        return root.getAsJsonObject("valid").get("username").getAsString();
    }

    // Returns valid password from JSON
    public String getValidPassword() {
        return root.getAsJsonObject("valid").get("password").getAsString();
    }

    // Returns invalid username from JSON
    public String getInvalidUsername() {
        return root.getAsJsonObject("invalid").get("username").getAsString();
    }

    // Returns invalid password from JSON
    public String getInvalidPassword() {
        return root.getAsJsonObject("invalid").get("password").getAsString();
    }
}
