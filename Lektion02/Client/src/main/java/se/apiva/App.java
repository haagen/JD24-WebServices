package se.apiva;

import com.fasterxml.jackson.databind.ObjectMapper;
import se.apiva.models.Post;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            App.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void run() throws IOException, InterruptedException {

        // Create a new HttpClient
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();

        // Create HttpRequest for a GET
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                .GET()
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> post1 = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(post1.statusCode());
        System.out.println(post1.body());


        // Example - do a POST with JSON
        //String jsonStr = "{ \"title\": \"Svart Krabba\", \"body\": \"En skitkass film, se den inte!\", ";
        //jsonStr += " \"userId\": 1 }";
        //System.out.println(jsonStr);

        ObjectMapper mapper = new ObjectMapper();
        Post post = new Post(1, 0, "Svart Krabba", "En skitkass film, se den inte!");
        String jsonStr = mapper.writeValueAsString(post);
        System.out.println(jsonStr);


        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonStr))
                .build();

        HttpResponse<String> post2 = client.send(postRequest, HttpResponse.BodyHandlers.ofString());

        /*
        System.out.println(post2.statusCode());
        System.out.println(post2.body());
         */
        if(post2.statusCode() >= 200 && post2.statusCode() < 300) {
            Post responsePost = mapper.readValue(post2.body(), Post.class);
            System.out.println("Post 2 after deserialization");
            System.out.println("Id: " + responsePost.getId());
            System.out.println(responsePost);
        } else {
            System.out.println("Remote responded with an error (" + post2.statusCode() + ")!");
        }

    }
}















