
import Controller.CardController;
import Controller.MainController;
import repository.CardRepository;
import repository.ProfileRepository;
import repository.TableRepository;
import service.InitService;

public class Main {
    public static void main(String[] args) {
        InitService initService = new InitService();
//        initService.initAdmin();

        MainController testController = new MainController();
//        testController.start();

//        ProfileController profileController = new ProfileController();
//        profileController.start();

        CardController cardController=new CardController();
        cardController.start();


    }
}