package dasturlash.uz.Controller;

import java.util.Scanner;

public class StaffController {
    public void start() {
        boolean b = true;
        while (b) {
            showMenu();
            int action = getAction();
            switch (action) {
                case 1:

                    break;
                case 2:

                    break;
                case 0:
                    b = false;
                    System.out.println("program finished!");
                    break;
            }
        }
    }
    public void showMenu() {
        System.out.println("*** Menu ***");
        System.out.println("1. Book");
        System.out.println("2. Category");
        System.out.println("3. Student ");
        System.out.println("4. Profile");
        System.out.println("0. Ext ");
    }
    public int getAction() {
        System.out.print("Enter action: ");
        Scanner scanner = new Scanner(System.in);
        int action = scanner.nextInt();
        return action;
    }
}
