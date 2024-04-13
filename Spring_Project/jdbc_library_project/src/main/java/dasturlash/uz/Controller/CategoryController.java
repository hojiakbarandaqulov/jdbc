package dasturlash.uz.Controller;

import dasturlash.uz.Container.ComponentContainer;
import dasturlash.uz.dto.Category;
import dasturlash.uz.service.CategoryService;

import javax.net.ssl.CertPathTrustManagerParameters;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CategoryController {
    public void start() {
        boolean b = true;
        while (b) {
            showMenu();
            int action = getAction();
            switch (action) {
                case 1:
                    addCategory();
                    break;
                case 2:
                    categoryList();
                    break;
                case 3:
                    deleteCategory();
                    break;
                case 0:
                    b = false;
                    System.out.println("Ext");
                    break;
            }
        }
    }

    public void addCategory() {
        System.out.println("Enter name: ");
        String name = ComponentContainer.scannerText.next();
        System.out.print("Enter created_date:(exm:2027-07-07): ");
        String exp_dateStr = ComponentContainer.scannerText.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate created_date = LocalDate.parse(exp_dateStr, formatter);

        System.out.println("Enter visible: ");
        Boolean visible = Boolean.valueOf(ComponentContainer.scannerText.next());

        Category category = new Category();
        category.setName(name);
        category.setCreatedDate(created_date);
        category.setVisible(visible);
        ComponentContainer.categoryService.addCategory(category);
    }

    public void deleteCategory() {
        System.out.print("Enter id: ");
        Integer id = ComponentContainer.scannerNumber.nextInt();
        ComponentContainer.categoryService.delete(id);
    }

    public void categoryList() {
        ComponentContainer.categoryService.CategoryList();
    }

    public void showMenu() {
        System.out.println("*** Category Menu ***");
        System.out.println("1. Add category");
        System.out.println("2. Category list");
        System.out.println("3. Delete category");
        System.out.println("0. Ext ");
    }

    public int getAction() {
        System.out.print("Enter action: ");
        Scanner scanner = new Scanner(System.in);
        int action = scanner.nextInt();
        return action;
    }
}
