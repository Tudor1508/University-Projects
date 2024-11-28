import Repository.Repository;
import Service.Service;
import UI.UI;

public class Main {
    public static void main(String[] args) {
        Repository repository = new Repository();
        Service service = new Service(repository);
        UI ui = new UI(service);
        ui.ShowUserInterface();
    }
}

