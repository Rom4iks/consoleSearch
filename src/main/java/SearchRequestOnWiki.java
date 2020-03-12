
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;


public class SearchRequestOnWiki {

    private List<String> requests = new ArrayList<>();
    private List<String> answer = new ArrayList<>();

    public void readRequestsList() {
        if (requests.isEmpty()) {
            System.out.println("Request History is empty");
        } else {
            System.out.println("Your request History : ");
            requests.forEach(System.out::println);
        }
    }

    public void readAnswers() {
        if (answer.isEmpty()) {
            System.out.println("Request History is empty");
        } else {
            System.out.println("Your responce History : ");
            answer.forEach(System.out::println);
        }
    }


    public void sendRestReques(Scanner scanner) throws IOException {
        System.out.println("Wikipedia search :");
        System.out.println("Please enter for search request");
        scanner.nextLine();
        String request = scanner.nextLine();
        requests.add(request);
        System.out.println("Enter the number of results");
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a number of requests");
            scanner.next();
        }
        int numberOfResults = scanner.nextInt();
        RestAssured.baseURI = "https://en.wikipedia.org/w/api.php?";

        Response response =
                RestAssured.given()
                        .queryParam("action", "opensearch")
                        .queryParam("limit", numberOfResults)
                        .queryParam("search", request)
                        .contentType(ContentType.JSON)
                        .get();

        String testBody = response.getBody().asString();
        String[] texts = testBody.replaceAll("[\\t\\n\\x0B\\f\\r\\[\\]\\\"\\\\]|(\\s\\s)", "").split(",");
        for (String text : texts
        ) {

            if (text.contains(request))
                System.out.println(text);
            answer.add(text);
        }
        String body = response.getBody().asString();
        scanner.nextLine();
        System.out.println("Should i write that result to the file? Type Y for agree.");
        String check = scanner.nextLine();
        FileWriter myWriter = new FileWriter("filename.txt");
        if (check.equalsIgnoreCase("Y")) {
            for (String text : texts
            ) {
                if (text.contains(request)) {
                    myWriter.write(text + "\n");
                }
            }
            System.out.println("Successfully wrote to the file.");
        } else
            System.out.println("Do not regret after !");
        myWriter.close();
    }
}

