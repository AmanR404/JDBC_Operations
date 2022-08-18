import java.sql.*;

class MyDatabaseException extends Exception{
    @Override
    public String getMessage() {
        return "Database not found";
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
            PreparedStatement preparedstmt = connection.prepareStatement("select * from employee_payroll");
            ResultSet resultSet = preparedstmt.executeQuery();

            while(resultSet.next()){
                System.out.println(resultSet.getString("name")+ " " + resultSet.getString("Salary") + " " + resultSet.getString("start_date"));
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        Assert.assertEquals("Aman", resultSet.getString("name"));
    }
}

