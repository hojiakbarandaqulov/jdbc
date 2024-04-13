package dasturlash.uz.Controller;

import dasturlash.uz.Container.ComponentContainer;
import dasturlash.uz.dto.Book;
import dasturlash.uz.service.BookService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BookController {
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
                    bookSearch();
                    break;
                case 3:
                    addBook();
                    break;
                case 4:
                    removeBook();
                    break;
                case 5:
                    showBooksOnHand();
                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 0:
                    b = false;
                    System.out.println("Ext");
                    break;
            }
        }
    }

    public void showBooksOnHand() {
        ComponentContainer.bookService.showBooksOnHand();
    }

    public void removeBook(){
        System.out.print("Enter book id: ");
        Integer id=ComponentContainer.scannerNumber.nextInt();
        ComponentContainer.bookService.removeBook(id);
    }
    public void bookSearch() {
        System.out.println("Enter search: ");
        String search=ComponentContainer.scannerText.next();
        ComponentContainer.bookService.searchBook(search);
    }

    public void bookList() {
        ComponentContainer.bookService.bookList();
    }

    public void addBook() {
        System.out.print("Enter title: ");
        String title= ComponentContainer.scannerText.next();
        System.out.print("Enter author: ");
        String author=ComponentContainer.scannerText.next();
        System.out.print("Enter category: ");
        Integer category=ComponentContainer.scannerNumber.nextInt();
        System.out.print("Enter available day: ");
        Integer available_day=ComponentContainer.scannerNumber.nextInt();
        System.out.print("Enter book publishDate: ");
        String publishDate=ComponentContainer.scannerText.next();

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setCategory(category);
        book.setAvailable_day(available_day);

        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate=LocalDate.parse(publishDate,formatter);
        book.setPublish_date(localDate.atStartOfDay());
        ComponentContainer.bookService.addBook(book);
    }

    public void showMenu() {
        System.out.println("*** Book Menu ***");
        System.out.println("1. Book list");
        System.out.println("2. Search");
        System.out.println("3. Add book");
        System.out.println("4. Remove book");
        System.out.println("5. Books on hand");
        System.out.println("6. Book history");
        System.out.println("7. Best books");
        System.out.println("0. Ext ");
    }
    public int getAction() {
        System.out.print("Enter action: ");
        Scanner scanner = new Scanner(System.in);
        int action = scanner.nextInt();
        return action;
    }
}
