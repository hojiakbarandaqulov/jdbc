import SmsPeckege.AddSms;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SmsManager {
    public static void createTable() {
        Connection connection = DatabaseUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("create table if not exists Sms(" +
                    " id int primary key," +
                    " SmsName varchar(25) not null," +
                    " SmsPhone varchar(25) not null unique ," +
                    " Sms varchar(255) not null " +
                    ")");
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addSms(AddSms addSms) {
        try {
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into sms(id,smsname,smsphone,sms) values (?,?,?,?)");
            preparedStatement.setInt(1, addSms.getId());
            preparedStatement.setString(2, addSms.getSmsName());
            preparedStatement.setString(3, addSms.getSmsPhone());
            preparedStatement.setString(4, addSms.getSms());

            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void smsList() {
        try {
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from sms");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String smsName = resultSet.getString("smsname");
                String smsPhone = resultSet.getString("smsphone");
                String sms = resultSet.getString("sms");
                System.out.println("id: " + id + " smsName: " + smsName + " smsPhone: " + smsPhone + " sms: " + sms);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteSms(Integer deleteId) {
        try {
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from sms where id=?");
            preparedStatement.setInt(1, deleteId);
            int effectedRows = preparedStatement.executeUpdate();
            System.out.println(effectedRows);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void searchSms(String smsName) {
        try {
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from sms where smsname like ?");
            preparedStatement.setString(1, "%" + smsName + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer id = resultSet.getInt("id");
                String smsname = resultSet.getString("smsname");
                String smsPhone = resultSet.getString("smsphone");
                String sms = resultSet.getString("sms");
                System.out.println("id: " + id + " smsName: " + smsname + " smsPhone: " + smsPhone + " sms: " + sms);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
