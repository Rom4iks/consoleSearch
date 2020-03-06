import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static io.restassured.RestAssured.given;

public class SearchRequestOnWiki {
    Scanner scanner = new Scanner(System.in);

    private List<String> requests;


    public void readRequestsList() {
        requests.forEach(System.out::println);
    }

    public void addNewRequestToTheList(String request) {
        requests.add(request);
    }

    public void sendRestReques() {
        scanner.nextLine();
        System.out.println("Please enter for search request");
//        String request = (scanner.nextLine()).replaceAll("\\s", "%20");

        String request = scanner.next();
        System.out.println("Enter the number of results");
        int numberOfResults = scanner.nextInt();
        RestAssured.baseURI = "https://en.wikipedia.org/w/api.php?";
        // Making GET request directly by RequestSpecification.get() method
        Response response = Arrays.asList(
                RestAssured.given()
                .queryParam("action", "opensearch")
                .queryParam("limit", numberOfResults)
                .queryParam("search", request)
                .contentType(ContentType.JSON)
                .get();

        //Retrieving Body of response
        String body = response.getBody().asString();
        //Retrieving Status Code of response
        int status = response.getStatusCode();


        String statusLine = response.getStatusLine();
        System.out.println("Response Body is " + body);
        System.out.println("Status code is " + status);
        System.out.println("Status line is " + statusLine);
    }
}

