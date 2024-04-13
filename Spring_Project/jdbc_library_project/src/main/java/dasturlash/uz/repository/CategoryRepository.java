package dasturlash.uz.repository;

import dasturlash.uz.DatabaseConnection.ConnectionUtil;
import dasturlash.uz.dto.Category;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class CategoryRepository {
    public List<Category> categoryList(){
        Connection connection = ConnectionUtil.getConnection();
        List<Category>categoryList=new LinkedList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select *from category");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                category.setCreatedDate(LocalDate.from(resultSet.getTimestamp("created_date").toLocalDateTime()));
                category.setVisible(resultSet.getBoolean("visible"));
                categoryList.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }
    public int deleteById(Integer id){
        Connection connection=ConnectionUtil.getConnection();
        try {
            Statement statement=connection.createStatement();
            int effectedRows = statement.executeUpdate("update category set visible = false where id=" + id);
            return effectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int addCategory(Category category) {
        Connection connection=ConnectionUtil.getConnection();
        int n=0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into category(name,created_date,visible)values (?,?,?)");
            preparedStatement.setString(1,category.getName());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(category.getCreatedDate().atStartOfDay()));
            preparedStatement.setBoolean(3,category.isVisible());
            n = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n;
    }
}
