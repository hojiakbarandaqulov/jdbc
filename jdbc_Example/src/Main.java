import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Contact>contactList=new LinkedList<>();
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        DataBaseUtil.createTable();
        boolean b = true;
        while (b) {
            showMenu();
            int action = getAction();
            switch (action) {
                case 1:
                    System.out.println("Add Contact");
                    addContact();
                    break;
                case 2:
                    System.out.println("Contact list");
                    DataBaseUtil.ContactList();
                    break;
                case 3:
                    System.out.println("Delete Contact");
                    System.out.print("Delete Contact Id: ");
                    Integer deleteId=scanner.nextInt();
                    DataBaseUtil.DeleteContact(deleteId);
                    break;
                case 4:
                    System.out.print("Search Contact: ");
                    String searchContact=scanner.next();
                    DataBaseUtil.searchContact(searchContact);
                    break;
                case 0:
                    System.out.println("Ext");
                    b = false;
                    break;
                default:
                    b = false;
            }
        }
    }

    public static void showMenu() {
        System.out.println(" *** Menu ***");
        System.out.println("1. Add Contact");
        System.out.println("2. Contact List");
        System.out.println("3. Delete Contact");
        System.out.println("4. Search Contact");
        System.out.println("0. Ext");
    }

    public static int getAction() {
        System.out.println("Enter action: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static void addContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter id: ");
        Integer id = scanner.nextInt();

        System.out.print("Enter name: ");
        String name = scanner.next();

        System.out.print("Enter surname: ");
        String surname = scanner.next();

        System.out.print("Enter phone: ");
        String phone = scanner.next();
        Contact contact=new Contact();
        contact.setName(name);
        contact.setSurname(surname);
        contact.setPhone(phone);
        ContactRepository contactRepository = new ContactRepository();
        boolean result = contactRepository.saveContact(contact);
        if (result){
            System.out.println("Contact added");
        }else {
            System.out.println("Something wend wrong.");
        }
    }
}