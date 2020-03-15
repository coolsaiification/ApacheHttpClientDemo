import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

class User {
    String id;
    String first_name;
    String email;
}

public class GetHandler {
    public static void main(String[] args) throws IOException {
        // Get request
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://reqres.in/api/users?page=1");
        HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
        System.out.println(httpResponse.getStatusLine());
        String json = EntityUtils.toString(httpResponse.getEntity());
        JsonObject jsonObject = new Gson().fromJson(json, JsonElement.class).getAsJsonObject();
        List<User> users = new Gson().fromJson(jsonObject.getAsJsonArray("data"),
                TypeToken.getParameterized(List.class, User.class).getType());
        users.forEach(user -> {
            System.out.println(user.id);
            System.out.println(user.email);
            System.out.println(user.first_name);
        });
    }
}
