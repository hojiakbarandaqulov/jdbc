package Controller;

import dto.CardDTO;
import dto.ProfileDTO;
import repository.ProfileRepository;
import service.CardService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CardController {
    private CardService cardService=new CardService();

    public void start() {
        ProfileRepository repository = new ProfileRepository();
        boolean b = true;
        while (b) {
            showMenu();
            int num = getNumber();
            switch (num) {
                case 1:
                    CreateCard();
                    break;
                case 2:
                    repository.getById(enterId());
                    break;
                case 3:
                    break;
                case 4:

                    break;
                case 0:
                    System.out.println("System end");
                    b = false;
                    break;
                default:
                    System.out.println("program finished!!!");
            }
        }
    }

    public void CreateCard() {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter number: ");
        Long number=scanner.nextLong();
        System.out.print("Enter expDate: ");
        String expDate= scanner.next();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate exp_Date=LocalDate.parse(expDate,formatter);

        cardService.createCard(number,exp_Date);

    }
    public void showMenu() {
        System.out.println("*** Profile Menu ***");
        System.out.println("1. Create Card");
        System.out.println("2. Card List");
        System.out.println("3. Update Card");
        System.out.println("4. Change Card status");
        System.out.println("5. Delete Card");
        System.out.println("0. Ext");
    }
    public int getNumber() {
        System.out.print("Chose number: ");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        return number;
    }
    public Integer enterId(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter id: ");
        Integer id=scanner.nextInt();
        return id;
    }
}

