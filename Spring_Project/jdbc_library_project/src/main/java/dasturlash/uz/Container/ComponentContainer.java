package dasturlash.uz.Container;

import dasturlash.uz.Controller.*;
import dasturlash.uz.repository.BookRepository;
import dasturlash.uz.repository.CategoryRepository;
import dasturlash.uz.repository.ProfileRepository;
import dasturlash.uz.service.AuthService;
import dasturlash.uz.service.BookService;
import dasturlash.uz.service.CategoryService;

import java.util.Scanner;

public class ComponentContainer {
    public static Scanner scannerText = new Scanner(System.in);
    public static Scanner scannerNumber = new Scanner(System.in);
    public static StudentController studentController = new StudentController();
    public static StaffController staffController = new StaffController();
    public static AdminController adminController = new AdminController();
    public static BookController bookController=new BookController();

    public static AuthService authService = new AuthService();
    public static CategoryService categoryService=new CategoryService();
    public static BookService bookService=new BookService();
    public static CategoryRepository categoryRepository=new CategoryRepository();
    public static CategoryController categoryController=new CategoryController();
    public static ProfileRepository profileRepository = new ProfileRepository();
    public static BookRepository bookRepository=new BookRepository();
}
