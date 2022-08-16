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
public class JDBCStatement {
    static Connection connection;
    static ResultSet resultSet;
    @Test
    public static void main(String[] args){
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
            Statement Stmt = connection.createStatement();
            String sql1 = "update employee_payroll set salary = '300000' where name = 'Govind'";
            Stmt.executeUpdate(sql1);
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        try{
            Statement Stmt2 = connection.createStatement();
            String sql2 = "select salary from employee_payroll where name = 'Govind'";
            resultSet = Stmt2.executeQuery(sql2);
            while(resultSet.next()){
                System.out.println(resultSet.getString("Salary"));
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        try{
            Assert.assertEquals(300000,resultSet.getString("Salary"));
        }
        catch(Exception e){
            System.out.println("Error..Unable to Test");
        }
    }
}

