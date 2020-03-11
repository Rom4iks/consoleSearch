
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;


public class SearchRequestOnWiki {

    private List<String> requests = new ArrayList<>();
    private List<String> answer = new ArrayList<>();

    public void readRequestsList() {
        System.out.println("Your request History : ");
        requests.forEach(System.out::println);
    }

    public void readAnswers () {
        System.out.println("Your responce History : ");
        answer.forEach(System.out::println);
    }

    public void sendRestReques(Scanner scanner) {

        System.out.println("Please enter for search request");
        scanner.nextLine();
        String request = scanner.nextLine();
        requests.add(request);
        System.out.println("Enter the number of results");
        int numberOfResults = scanner.nextInt();
        RestAssured.baseURI = "https://en.wikipedia.org/w/api.php?";
        // Making GET request directly by RequestSpecification.get() method
        Response response =
                RestAssured.given()
                        .queryParam("action", "opensearch")
                        .queryParam("limit", numberOfResults)
                        .queryParam("search", request)
                        .contentType(ContentType.JSON)
                        .get();

        //Retrieving Body of response
//        String body = response.getBody().asString();
        String body = response.getBody().prettyPrint();
        answer.add(body);
        //Retrieving Status Code of response
        int status = response.getStatusCode();


        String statusLine = response.getStatusLine();
//        System.out.println("Response Body is " + body);
        System.out.println("Status code is " + status);
        System.out.println("Status line is " + statusLine);
    }

    public void sendRestReques2(Scanner scanner) {
        System.out.println("Please enter for search request");
        scanner.nextLine();
        String request = scanner.nextLine();
        requests.add(request);
        System.out.println("Enter the number of results");
        int numberOfResults = scanner.nextInt();
        RestAssured.baseURI = "https://en.wikipedia.org/w/api.php?";
        // Making GET request directly by RequestSpecification.get() method
        Response response =
                RestAssured.given()
                        .queryParam("action", "opensearch")
                        .queryParam("limit", numberOfResults)
                        .queryParam("search", request)
                        .contentType(ContentType.JSON)
                        .get();

        //Retrieving Body of response
        String json = response.getBody().asString();
        JSONObject obj = new JSONObject(json);
        String pageName = obj.getJSONObject(request).getString(request);

        System.out.println(pageName);

        JSONArray arr = obj.getJSONArray("posts");
        for (int i = 0; i < arr.length(); i++) {
            String post_id = arr.getJSONObject(i).getString("post_id");
            System.out.println(post_id);
        }


    }
}

