import java.sql.*;
import org.junit.Assert;
import org.junit.Test;
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
    static String idarr[] = new String[3];
    static String namearr[] = new String[3];
    static String salaryarr[] = new String[3];
    @Test
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
            PreparedStatement preparedstmt1 = connection.prepareStatement("insert into employee_payroll (id,name,salary) values (3,'Ameen',40000)");
            preparedstmt1.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.toString());
        }

        try{
            PreparedStatement preparedStmt2 = connection.prepareStatement("select * from employee_payroll");
            resultSet = preparedStmt2.executeQuery();
            int i = 0;
            while(resultSet.next()){
                idarr[i] = resultSet.getString("id");
                namearr[i] = resultSet.getString("name");
                salaryarr[i] = resultSet.getString("salary"); 
                i++;
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        for(int i = 0;i < idarr.length;i++){
            System.out.print(idarr[i] + " ");
            System.out.print(namearr[i] + " ");
            System.out.print(salaryarr[i] + " \n");
        }
        Assert.assertEquals("Aman", namearr[0]);
        }
}

