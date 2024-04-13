package controller;

import dto.ContactDTO;
import service.ContactService;
import util.DataBaseUtil;
import java.util.List;
import java.util.Scanner;

public class ContactController {
    private ContactService contactService;


    public ContactController() {
        contactService = new ContactService();
    }

    public void start() {
        DataBaseUtil.createTable();
        boolean b = true;
        while (b) {
            showMenu();
            int action = getAction();
            switch (action) {
                case 1:
                    System.out.println("Add contact");
                    addContact();
                    break;
                case 2:
                    System.out.println("Contact list");
                    contactList();
                    break;
                case 3:
                    System.out.println("Delete contact");
                    deleteContact();
                    break;
                case 4:
                    System.out.println("Search contact");
                    search();
                    break;
                case 0:
                    System.out.println("Exit");
                    b = false;
                    break;
                default:
                    b = false;
                    break;
            }
        }
        System.out.println("The end");
    }
    public void showMenu() {
        System.out.println("*** Menu ***");
        System.out.println("1. Add contact");
        System.out.println("2. Contact List");
        System.out.println("3. Delete dto.Contact");
        System.out.println("4. Search dto.Contact");
        System.out.println("0. Exit");
    }

    public int getAction() {
        System.out.println("Enter action:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void addContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = scanner.next();

        System.out.println("Enter surname: ");
        String surname = scanner.next();

        System.out.println("Enter phone: ");
        String phone = scanner.next();

        // ContactDTO contactDTO = new ContactDTO();
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setName(name);
        contactDTO.setSurname(surname);
        contactDTO.setPhone(phone);

        contactService.save(contactDTO);
    }

    public void contactList() {
        contactService.contactList();
    }

    public void deleteContact() {
        System.out.println("Enter phone");
        Scanner scanner = new Scanner(System.in);
        String phone = scanner.next();
        contactService.deleteContact(phone);
    }

    public void search() {
        System.out.println("Enter query:");
        Scanner scanner = new Scanner(System.in);
        String query = scanner.next();
        contactService.search(query);
    }
}
