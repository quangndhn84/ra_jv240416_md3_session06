package ra.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/studentManagement";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "1234$";
    //Phương thức khởi tạo đối tượng connection để làm việc với database MySQL
    public static Connection openConnection(){
        Connection conn = null;
        try {
            //1. set Driver cho DriverManager
            Class.forName(DRIVER);
            //2. Khởi tạo đối tượng Connection từ DriverManager
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return conn;
    }
    //Phương thức đóng connection, callableStatement
    public static void closeConnection(Connection conn, CallableStatement callSt){
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (callSt!=null){
            try {
                callSt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
