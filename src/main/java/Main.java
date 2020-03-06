import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SearchRequestOnWiki sr = new SearchRequestOnWiki();
        int menuButton = 0;
        Menu.printMenu();

        boolean flag = true;

        while (flag) {
            System.out.println("Please pick your option");
            menuButton = scanner.nextInt();
            {
            }
            switch (menuButton) {
                case 0:
                    Menu.printMenu();
                    break;
                case 1:
                    sr.sendRestReques();
                    break;
                case 2:


//                    SearchRequestOnWiki.readRequestsList();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    flag = false;
                    scanner.close();
                    break;
            }
        }
    }
}
