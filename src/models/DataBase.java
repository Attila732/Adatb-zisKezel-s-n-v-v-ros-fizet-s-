package models;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBase {

    public DataBase()  {
        Employee emp=new Employee(1, "Marduk Károly","Miskolc", 395);
        this.instertEmployee(emp);
    }
    //hibakezelő
    public void instertEmployee(Employee emp){
    try {
        tryinstertEmployee(emp);
 
    } catch (SQLException e) {
        System.err.println("Hiba! Az adatbázishoz kapcsolódás sikertelen!");
        System.out.println(e.getMessage());
    } catch(ClassNotFoundException e){
        System.err.println("Nincs a mariaDb driver betöltve");
        System.out.println(e.getMessage());
    }
    }
    //iparikód hasznos kód
    public void tryinstertEmployee(Employee emp) throws SQLException, ClassNotFoundException {
        
        Connection conn =null;
        String url = "jdbc:mariadb://localhost:3306/hum";
      
        Class.forName("org.mariadb.jdbc.Driver");
        conn = DriverManager.getConnection(url, "hum", "titok");
        System.out.println("Működik");
        String sql ="insert into Employees"+
        "(name, city, salary) values"+
        "(?,  ?, ?)";
        //('Pali, 'Szeged', '347')
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, emp.name);
        pstmt.setString(2, emp.city);
        pstmt.setDouble(3, emp.salary);
        pstmt.execute();
        conn.close() ;
        }
    }

