package ra.run;

import ra.business.StudentBusiness;
import ra.entity.Student;

import java.util.List;
import java.util.Scanner;

public class StudentManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("***************MENU*******************");
            System.out.println("1. Danh sách sinh viên");
            System.out.println("2. Thêm mới sinh viên");
            System.out.println("3. Cập nhật sinh viên");
            System.out.println("4. Xóa sinh viên");
            System.out.println("5. Thống kê số lượng sinh viên");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    List<Student> listStudents = StudentBusiness.findAll();
                    listStudents.stream().forEach(System.out::println);
                    break;
                case 2:
                    Student student = new Student();
                    System.out.println("Nhập vào tên sinh viên:");
                    student.setStudentName(scanner.nextLine());
                    System.out.println("Nhập vào tuổi sinh viên:");
                    student.setAge(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Nhập vào trạng thái sinh viên:");
                    student.setStatus(Boolean.parseBoolean(scanner.nextLine()));
                    boolean result = StudentBusiness.save(student);
                    if (result) {
                        System.out.println("Thêm mới thành công");
                    } else {
                        System.err.println("Có lỗi trong quá trình thêm mới");
                    }
                    break;
                case 3:
                    System.out.println("Nhập vào mã sinh viên cần cập nhật:");
                    int studentId = Integer.parseInt(scanner.nextLine());
                    Student studentUpdate = StudentBusiness.findById(studentId);
                    if (studentUpdate != null) {
                        System.out.println("Nhập tên sinh viên cập nhật:");
                        studentUpdate.setStudentName(scanner.nextLine());
                        System.out.println("Nhập tuổi sinh viên cập nhật:");
                        studentUpdate.setAge(Integer.parseInt(scanner.nextLine()));
                        System.out.println("Nhập trạng thái sinh viên cập nhật:");
                        studentUpdate.setStatus(Boolean.parseBoolean(scanner.nextLine()));
                        //Thực hiện cập nhật
                        boolean resultUpdate = StudentBusiness.update(studentUpdate);
                        if (resultUpdate) {
                            System.out.println("Cập nhật thành công");
                        } else {
                            System.err.println("Có lỗi trong quá trình cập nhât");
                        }
                    } else {
                        System.err.println("Mã sinh viên không tồn tại");
                    }
                    break;
                case 4:
                    System.out.println("Nhập vào mã sinh viên cần xóa:");
                    int studentIdDelete = Integer.parseInt(scanner.nextLine());
                    Student studentDelete = StudentBusiness.findById(studentIdDelete);
                    if (studentDelete != null) {
                        //Thực hiện xóa
                        boolean resultDelete = StudentBusiness.delete(studentIdDelete);
                        if (resultDelete) {
                            System.out.println("Xóa thành công");
                        } else {
                            System.err.println("Có lỗi xảy ra trong quá trình xóa");
                        }
                    } else {
                        System.err.println("Mã sinh viên không tồn tai");
                    }
                    break;
                case 5:
                    int cntStudent = StudentBusiness.getCntStudent();
                    System.out.printf("Có %d sinh viên\n", cntStudent);
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-6");
            }
        } while (true);
    }
}
