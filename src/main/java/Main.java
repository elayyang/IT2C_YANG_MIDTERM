
import com.yourdomain.dao.ScenarioDao;
import com.yourdomain.ui.ScenarioMenu;

public class Main {

    public static void main(String[] args) {

        ScenarioDao dao = new ScenarioDao();
        ScenarioMenu menu = new ScenarioMenu();

        dao.createTable();

        boolean running = true;

        while (running) {

            menu.displayMenu();
            int choice = menu.getChoice();

            switch (choice) {

                case 1:
                    menu.addRecord();
                    break;

                case 2:
                    menu.viewRecords();
                    break;

                case 3:
                    System.out.println("Exiting system. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please enter 1, 2, or 3."
                    );
            }
        }
    }
}
