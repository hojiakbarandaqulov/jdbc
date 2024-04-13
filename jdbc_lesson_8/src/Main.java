import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Movie> movieList = getMovieList();
//        System.out.println(movieList);
        movieList.forEach(System.out::println);
    }

    public static List<Movie> getMovieList() {
        List<Movie> movieList = new LinkedList<>();
        Connection connection = null;
        try {
            connection = DataBaseUtil.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from movie");
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                Long duration = resultSet.getLong("duration");
                LocalDateTime createData = resultSet.getTimestamp("created_date").toLocalDateTime();
                LocalDate publishDate = resultSet.getDate("publish_date").toLocalDate();
                Double rating = resultSet.getDouble("rating");

                Movie movie = new Movie();
                movie.setId(resultSet.getInt("id"));
                movie.setTitle(resultSet.getString("title"));
                movie.setDuration(resultSet.getLong("duration"));
                movie.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
                movie.setPublishDate(resultSet.getDate("publish_date").toLocalDate());
                movie.setRating(resultSet.getDouble("rating"));
                movieList.add(movie);
                System.out.println(id + " | " + title + " | " + duration + " | " + createData + " | " + publishDate + " | " + rating);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return movieList;
    }

    public static List<Movie> getMovieListWithBigRating() {
        List<Movie> movieList = new LinkedList<>();
        Connection connection = null;
        try {
            connection = DataBaseUtil.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from movie where rating>7.7");
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                Long duration = resultSet.getLong("duration");
                LocalDateTime createData = resultSet.getTimestamp("created_date").toLocalDateTime();
                LocalDate publishDate = resultSet.getDate("publish_date").toLocalDate();
                Double rating = resultSet.getDouble("rating");
                Movie movie = new Movie();
                movie.setId(resultSet.getInt("id"));
                movie.setTitle(resultSet.getString("title"));
                movie.setDuration(resultSet.getLong("duration"));
                movie.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
                movie.setPublishDate(resultSet.getDate("publish_date").toLocalDate());
                movie.setRating(resultSet.getDouble("rating"));
                movieList.add(movie);
                System.out.println(id + " | " + title + " | " + duration + " | " + createData + " | " + publishDate + " | " + rating);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return movieList;
    }
}
