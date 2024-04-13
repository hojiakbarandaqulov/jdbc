package dasturlash.uz.repository;

import dasturlash.uz.DatabaseConnection.ConnectionUtil;
import dasturlash.uz.dto.Book;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class BookRepository {
//    public Book getBookById(Integer id) {
//        Connection connection = ConnectionUtil.getConnection();
//        Book book = null;
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("select * from book where id=?");
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                book = new Book();
//                book.setTitle(resultSet.getString("title"));
//                book.setAuthor(resultSet.getString("author"));
//                book.setCategory(resultSet.getInt("category"));
//                book.setAvailable_day(resultSet.getInt("available_day"));
//                book.setPublish_date(String.valueOf(resultSet.getTimestamp("publish_date")));
//            } else {
//                book = null;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            if (connection!=null){
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return book;
//    }

    public void addBook(Book book) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into book(title,author,category,available_day,publish_date)values (?,?,?,?,?)");
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getCategory());
            preparedStatement.setInt(4, book.getAvailable_day());

            preparedStatement.setTimestamp(5, Timestamp.valueOf(book.getPublish_date()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public List<Book> bookList() {
        Connection connection = ConnectionUtil.getConnection();
        List<Book> bookList = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select *from book");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setCategory(resultSet.getInt("category"));
                book.setAvailable_day(resultSet.getInt("available_day"));
                book.setPublish_date(resultSet.getTimestamp("publish_date").toLocalDateTime());
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public List<Book> search(String query) {
        Connection connection = null;
        List<Book> bookList = new LinkedList<>();
        try {
            connection = ConnectionUtil.getConnection();
            String sql = "SELECT * FROM book WHERE LOWER(title) LIKE  ? OR LOWER(author) LIKE  ?;";
            String param = "%" + query + "%";
            param = param.toLowerCase();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, param);
            preparedStatement.setString(2, param);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setCategory(resultSet.getInt("category"));
                book.setAvailable_day(resultSet.getInt("available_day"));
                book.setPublish_date(resultSet.getTimestamp("publish_date").toLocalDateTime());
                bookList.add(book);
            }
            return bookList;
        } catch (SQLException e) {
            e.printStackTrace();
            return bookList;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void removeBook(Integer id) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from book where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Book> showBooksOnHand() {
        Connection connection=ConnectionUtil.getConnection();
        String query = "select * from taken_book";
        List<Book>bookList=new LinkedList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book=new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle("title");
                book.setAuthor("author");
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bookList;
    }
}
