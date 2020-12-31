package ajdbctest;

import java.sql.*;
import java.util.Scanner;

/*
    演示只能使用Statement的功能需求：
        输入参数使查询结果升序或者是降序输出，需要将升序降序关键字的字符串拼接到SQL语句中

 */
public class JDBCTest07 {

    public static void main(String[] args) {

//        //用户输入控制查询结果升序降序的信息
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入关键字信息：升序asc，降序desc");
//        String info = scanner.nextLine();
//
//        //执行SQL
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        try {
//
//            Class.forName("com.mysql.jdbc.Driver");
//
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlstudy?useSSL=false","root","rong195302");
//
//            String sql = "select sal from emp order by sal ?";
//            ps = conn.prepareStatement(sql);
//            ps.setString(1,info);
//
//            rs = ps.executeQuery();
//
//            while (rs.next()){
//                String sal = rs.getString(1);
//                System.out.println(sal);
//            }
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }finally {
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
//            if (ps != null) {
//                try {
//                    ps.close();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
//        }


        //用户输入控制查询结果升序降序的信息
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入关键字信息：升序asc，降序desc");
        String info = scanner.nextLine();

        //执行SQL
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlstudy?useSSL=false","root","rong195302");

            stmt = conn.createStatement();

            String sql = "select sal from emp order by sal "+info;
            rs = stmt.executeQuery(sql);

            while (rs.next()){
                String sal = rs.getString(1);
                System.out.println(sal);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }

}
