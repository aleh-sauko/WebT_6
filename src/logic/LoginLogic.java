package logic;

import manager.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginLogic {

    public static boolean registerUser(String login, String password) {

        boolean status = false;
        Connection connection = null;

        try {
            connection = DatabaseManager.getInstance().getConnection();

            PreparedStatement st = connection.prepareStatement("INSERT INTO USERS(LOGIN, PASSWORD) VALUES (?, ?)");
            st.setString(1, login);
            st.setString(2, password);

            status = (st.executeUpdate() != 0);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {}
            }
        }
        return status;
    }


    public static boolean checkLogin(String login, String password) {

        boolean status = false;
        Connection connection = null;

        try {
            connection = DatabaseManager.getInstance().getConnection();

            PreparedStatement st = connection.prepareStatement("SELECT * FROM USERS WHERE LOGIN = ? AND PASSWORD = ?");
            st.setString(1, login);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();

            status = rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {}
            }
        }

        return status;
    }
}