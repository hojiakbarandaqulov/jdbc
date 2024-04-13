import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Database database = new Database();
        boolean b = true;
        while (b) {
            menu();
            switch (action()) {
                case 1:
                    database.addContact(variables());
                    break;
                case 2:
                    database.ContactList();
                    break;
                case 3:
                    database.searchContact(enterSearch());
                    break;
                case 4:
                    database.deleteContact(enterDelete());
                    break;
                case 0:
                    b = false;
                    System.out.println("program finished!!!");
            }
        }
    }

    public static Integer enterDelete() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter delete: ");
        Integer delete = scanner.nextInt();
        return delete;
    }

    public static String enterSearch() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter search: ");
        String search = scanner.next();
        return search;
    }

    public static User variables() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id: ");
        Integer id = scanner.nextInt();
        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter surname: ");
        String surname = scanner.next();
        System.out.print("Enter phone: ");
        Integer phone = scanner.nextInt();

        User user = new User(id, name, surname, phone);
        return user;
    }

    public static int action() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter action: ");
        int action = scanner.nextInt();
        return action;
    }

    public static void menu() {
        System.out.println("*** Contact manager ***");
        System.out.println("*** Menu ***");
        System.out.println("1. Add Contact");
        System.out.println("2. Contact List");
        System.out.println("3. Search Contact");
        System.out.println("4. Delete Contact ");
        System.out.println("0. Exit");
    }
}