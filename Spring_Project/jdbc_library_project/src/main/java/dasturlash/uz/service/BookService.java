package dasturlash.uz.service;

import dasturlash.uz.Container.ComponentContainer;
import dasturlash.uz.dto.Book;

import java.util.LinkedList;
import java.util.List;

public class BookService {
    // git config --global user.name "hojiakbarandaqulov"
    // git config --global user.email "hojiakbarandaqulov5@gmail.com"
    public void addBook(Book book){
        /*Book bookById = ComponentContainer.bookRepository.getBookById(book.getId());
        if (b)*/
        ComponentContainer.bookRepository.addBook(book);
    }
    public void bookList(){
        List<Book> bookList = ComponentContainer.bookRepository.bookList();
        for (Book book: bookList){
            System.out.println(book.toString());
        }
    }
    public void searchBook(String query){
        List<Book> search = ComponentContainer.bookRepository.search(query);
        for (Book book:search){
            System.out.println(book.toString());
        }
    }
    public void removeBook(Integer id){
        ComponentContainer.bookRepository.removeBook(id);
    }
    public void showBooksOnHand(){
        List<Book>bookList=new LinkedList<>();
        for (Book book:bookList){
            System.out.println(book.toString());
        }
        ComponentContainer.bookRepository.showBooksOnHand();
    }
}
