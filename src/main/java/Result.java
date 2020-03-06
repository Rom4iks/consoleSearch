import java.util.List;

public class Result {

    private String text;

    private String links;

    public Result(String text, String links) {
        this.text = text;
        this.links = links;
    }

    public String getText() {
        return text;
    }

    public String getLinks() {
        return links;
    }
}
