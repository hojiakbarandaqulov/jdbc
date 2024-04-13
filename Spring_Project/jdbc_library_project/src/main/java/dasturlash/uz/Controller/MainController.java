package dasturlash.uz.Controller;

import dasturlash.uz.service.AuthService;
import dasturlash.uz.service.InitService;
import dasturlash.uz.dto.Profile;
import dasturlash.uz.repository.TableRepository;

import java.util.Scanner;

public class MainController {
    private Scanner scannerStr;
    private Scanner scannerInt;
    public MainController() {
        scannerStr = new Scanner(System.in);
        scannerInt = new Scanner(System.in);
    }
    public void start() {
        TableRepository tableRepository = new TableRepository();
        InitService initService = new InitService();
        tableRepository.createTables();
        initService.initAdmin();
        initService.initTestStudent();
        boolean b = true;
        while (b) {
            showMenu();
            int action = getAction();
            switch (action) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:
                    byCategory();
                    break;
                case 4:
                    login();
                    break;
                case 5:
                    registration();
                    break;
                case 0:
                    b = false;
                    System.out.println("program finished!");
                    break;
            }
        }
    }

    public  void byCategory() {
        System.out.print("Enter name: ");
        String name=scannerStr.next();
        
    }

    public void registration() {
        System.out.print("Enter name: ");
        String name=scannerStr.next();
        System.out.print("Enter surname: ");
        String surname=scannerStr.next();
        System.out.print("Enter phone: ");
        String phone=scannerStr.next();
        System.out.print("Enter login: ");
        String login=scannerStr.next();
        System.out.print("Enter password: ");
        String password=scannerStr.next();

        Profile profile = new Profile();
        profile.setName(name);
        profile.setSurname(surname);
        profile.setPhone(phone);
        profile.setLogin(login);
        profile.setPassword(password);
        AuthService authService = new AuthService();
        authService.registration(profile);
    }

    public void showMenu() {
        System.out.println("*** Main Menu ***");
        System.out.println("1. BookList");
        System.out.println("2. Search");
        System.out.println("3. By category ");
        System.out.println("4. Login ");
        System.out.println("5. Registration ");
        System.out.println("0. Ext ");
    }

    public int getAction() {
        System.out.print("Enter action: ");
        int action = scannerInt.nextInt();
        return action;
    }

    public void login() {
        System.out.println("Enter login: ");
        String login = scannerStr.next();

        System.out.println("Enter password");
        String password = scannerStr.next();

        AuthService authService = new AuthService();
        authService.login(login, password);
    }
}
