import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        SearchRequestOnWiki sr = new SearchRequestOnWiki();

        Menu.printMenu();

        boolean flag = true;

        while (flag) {
            System.out.println("Please pick your option from the menu");
            while (!scanner.hasNextInt()) {
                System.out.println("Please press number  from 0 to 5");
                scanner.next();
            }
            int menuButton = scanner.nextInt();
            switch (menuButton) {
                case 0:
                    Menu.printMenu();
                    break;
                case 1:
                    sr.sendRestReques(scanner);
                    break;
                case 2:
                    System.out.println("Feature on development stage. But we asked u not to press this menu button!");
                    break;
                case 3:
                    sr.readAnswers();
                    break;
                case 4:
                    sr.readRequestsList();
                    break;
                case 5:
                    flag = false;
                    scanner.close();
                    break;
            }
        }
    }
}
