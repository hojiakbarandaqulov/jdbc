package dasturlash.uz.Controller;

import dasturlash.uz.Container.ComponentContainer;

import java.util.Scanner;

public class StudentController {
    public void start() {
        boolean b = true;
        while (b) {
            showMenu();
            int action = getAction();
            switch (action) {
                case 1:
                    bookList();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    break;
                case 5:

                    break;
                case 0:
                    b = false;
                    System.out.println("program finished!");
                    break;
            }
        }
    }

    public void bookList() {
//        ComponentContainer.
    }

    public void showMenu() {
        System.out.println("*** Student Menu ***");
        System.out.println("1. BookList");
        System.out.println("2. Search");
        System.out.println("3. Take book ");
        System.out.println("4. Return book ");
        System.out.println("5. Books on hand ");
        System.out.println("6. Take book history");
        System.out.println("0. Ext ");
    }

    public int getAction() {
        System.out.print("Enter action: ");
        Scanner scanner = new Scanner(System.in);
        int action = scanner.nextInt();
        return action;
    }
}
