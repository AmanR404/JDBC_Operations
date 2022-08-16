import java.sql.*;
public class JDBCPreparedStatement {
    static Connection connection;
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
        String userName = "root";
        String password = "Arai@3313";

        try{
            System.out.println("Connecting to Database : " + jdbcURL);
            connection = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection is Successful : " + connection);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

