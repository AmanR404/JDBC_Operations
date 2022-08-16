import java.sql.*;
import org.junit.*;
class MyDatabaseException extends Exception{
    @Override
    public String getMessage() {
        return "Database not found";
    }
    @Override
    public String toString() {
        return "Error...Unable to update";
    }
}
public class JDBCPreparedStatement {
    static Connection connection;
    @Test
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
        String userName = "root";
        String password = "Arai@3313";

        try{
            System.out.println("Connecting to Database : " + jdbcURL);
            connection = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection is Successful : " + connection);
            System.out.println(" ");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            PreparedStatement preparedStmt = connection.prepareStatement("update employee_payroll set salary = '300000' where name = 'Govind'");
            preparedStmt.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        try{
            PreparedStatement preparedStmt2 = connection.prepareStatement("select salary from employee_payroll where name = 'Govind'");
            ResultSet resultSet = preparedStmt2.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getString("Salary"));
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(300000,resultSet.getString("Salary"));
    }
}

