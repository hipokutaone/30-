package papki;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class DatabaseHandler extends Config {
    Connection dbConnection;

    public  Connection getDbConnection()
            throws  ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://127.0.0.1:3306/mysor?";

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, "root", "12345" );

        return dbConnection;
    }

    public void signUpUser(User user) {
        String insert = "INSERT INTO" + Const.USER_TABEL + "(" + Const.USER_USER +
                "," + Const.USER_PASSSWORD + ")" + "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getUserName());
            prSt.setString(2, user.getPassword());

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getUser(User user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM" + Const.USER_TABEL + "WHERE" +
                Const.USER_USER + "=? AND" + Const.USER_PASSSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getUserName());
            prSt.setString(2, user.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    public void signUpUser(String text, String text1) {
    }
}
