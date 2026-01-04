package dataprovider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataProvider {

    private Connection connection;

    public void open() {
        String strServer = "localhost";
        String strDatabase = "postgres";
        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://" + strServer + ":5432/" + strDatabase;

            connection = DriverManager.getConnection(url, "admin", "password");
            connection.setAutoCommit(true);
            System.out.println("Kết nối PostgreSQL thành công!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        try {
            Statement sm = connection.createStatement();
            rs = sm.executeQuery(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    public int executeUpdate(String sql) {
        int n = -1;
        try {
            Statement sm = connection.createStatement();
            n = sm.executeUpdate(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }

}
