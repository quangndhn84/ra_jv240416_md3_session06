package ra.business;

import ra.entity.Student;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class StudentBusiness {
    public static List<Student> findAll() {
        //Khai báo đối tượng Connection và CallableStatement
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> listStudents = null;
        try {
            //- Khởi tạo đối tượng conn
            conn = ConnectionDB.openConnection();
            //- Khởi tạo đối tượng callSt từ conn
            callSt = conn.prepareCall("{call get_all_student()}");
            //- Thực hiện gọi procedure và trả ra kết quả
            ResultSet rs = callSt.executeQuery();
            //- Duyệt rs và lấy dữ liệu đẩy vào listStudents
            listStudents = new ArrayList<Student>();
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setStudentName(rs.getString("student_name"));
                student.setAge(rs.getInt("age"));
                student.setStatus(rs.getBoolean("student_status"));
                listStudents.add(student);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //Đóng kết nối và callSt
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listStudents;
    }

    public static Student findById(int studentId) {
        //Khai báo đối tượng Connection và CallableStatement
        Connection conn = null;
        CallableStatement callSt = null;
        Student student = null;
        try {
            //- Khởi tạo đối tượng conn
            conn = ConnectionDB.openConnection();
            //- Khởi tạo đối tượng callSt từ conn
            callSt = conn.prepareCall("{call get_Student_By_id(?)}");
            callSt.setInt(1, studentId);
            //- Thực hiện gọi procedure và trả ra kết quả
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setStudentName(rs.getString("student_name"));
                student.setAge(rs.getInt("age"));
                student.setStatus(rs.getBoolean("student_status"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //Đóng kết nối và callSt
            ConnectionDB.closeConnection(conn, callSt);
        }
        return student;
    }

    public static boolean save(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call insert_student(?,?,?)}");
            //Set giá trị cho các tham số vào
            callSt.setString(1, student.getStudentName());
            callSt.setInt(2, student.getAge());
            callSt.setBoolean(3, student.isStatus());
            //Đăng ký kiểu dữ liệu cho các tham số trả ra
            //Thực hiện gọi procedure
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean update(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_student(?,?,?,?)}");
            //Set giá trị cho các tham số vào
            callSt.setInt(1, student.getStudentId());
            callSt.setString(2, student.getStudentName());
            callSt.setInt(3, student.getAge());
            callSt.setBoolean(4, student.isStatus());
            //Đăng ký kiểu dữ liệu cho các tham số trả ra
            //Thực hiện gọi procedure
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean delete(int studentId) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_Student(?)}");
            //Set giá trị cho các tham số vào
            callSt.setInt(1, studentId);
            //Đăng ký kiểu dữ liệu cho các tham số trả ra
            //Thực hiện gọi procedure
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static int getCntStudent() {
        Connection conn = null;
        CallableStatement callSt = null;
        int cntStudent = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_cnt_student(?)}");
            //Set giá trị cho các tham số vào
            //Đăng ký kiểu dữ liệu cho các tham số trả ra
            callSt.registerOutParameter(1, Types.INTEGER);
            //Thực hiện gọi procedure
            callSt.execute();
            //Lấy giá trị các tham số trả ra
            cntStudent = callSt.getInt(1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return cntStudent;
    }
}
