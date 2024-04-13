package Controller;

import repository.ProfileRepository;
import repository.TableRepository;
import service.AuthService;


import java.util.Scanner;

public class MainController {
    private AuthService authService = new AuthService();

    public void start() {
        TableRepository tableRepository = new TableRepository();
        ProfileRepository profileRepository = new ProfileRepository();
        tableRepository.createTable();
        boolean b = true;
        while (b) {
            showMenu();
            int num = getNumber();
            switch (num) {
                case 1:
                    login();
                    break;
                case 2:
                    registration();
                    break;
                case 3:

                    break;
                case 0:
                    System.out.println("System end");
                    b = false;
                    break;
                default:
                    System.out.println("Program finished!!!");
            }
        }
    }
    public void registration() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter surname: ");
        String surname = scanner.next();
        System.out.print("Enter phone: ");
        String phone = scanner.next();
        System.out.print("Enter pswd: ");
        String pswd = scanner.next();
        authService.registration(name, surname, phone, pswd);
    }

    public void showMenu() {
        System.out.println("*** Menu ***");
        System.out.println("1. Login");
        System.out.println("2. Registration");

    }

    public int getNumber() {
        System.out.print("Chose number: ");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        return number;
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the phone : ");
        String phone = scanner.nextLine();
        System.out.print("Enter the password : ");
        String password = scanner.nextLine();
        authService.login(phone, password);
    }
}

