import java.sql.*;
class MyDatabaseException extends Exception{
    @Override
    public String getMessage() {
        return "Unable to Retrieve DB";
    }
    @Override
    public String toString() {
        return "Error...Unable to update";
    }
}
public class JDBCStatement {
    static Connection connection;
    static ResultSet resultSet;
    public static void main(String[] args) throws SQLException{
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
            PreparedStatement preparedstmt1 = connection.prepareStatement("alter table employee_payroll add is_active BOOLEAN after start_date");
            preparedstmt1.executeUpdate();
            connection.prepareStatement("update * from employee_payroll set is_active = true").executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.toString());
        }

        try{
            PreparedStatement preparedStmt2 = connection.prepareStatement("update employee_payroll set is_active = false where name = 'Ameen'");
            preparedStmt2.executeUpdate();
            ResultSet resultSet =  connection.prepareStatement("select * from employee_payroll where is_active = true").executeQuery();
            while(resultSet.next()){
              System.out.println(resultSet.getString("id")+ " " + resultSet.getString("name") + " " + resultSet.getString("salary"));
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        }
}

