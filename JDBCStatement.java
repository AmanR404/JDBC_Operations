import java.sql.*;
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
            PreparedStatement preparedStmt2 = connection.prepareStatement("select * from employee_payroll where start between cast('2022-08-12' as date) and date(now())");
            resultSet = preparedStmt2.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getString("name")+ " " + resultSet.getString("salary"));
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

