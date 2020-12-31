package ajdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
    使用PreparedStatement进行增删改
 */
public class JDBCTest08 {

    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlstudy?useSSL=false","root","rong195302");

            //增
//            String sql = "insert into dept(deptno,dname,loc) values (?,?,?)";
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1,50);
//            ps.setString(2,"销售部");
//            ps.setString(3,"北京");

            //改
//            String sql = "update dept set dname = ?,loc = ? where deptno = ?";
//            ps = conn.prepareStatement(sql);
//            ps.setString(1,"技术部");//要注意下标
//            ps.setString(2,"大唐");
//            ps.setInt(3,50);

            //删
            String sql ="delete from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,50);

            int count = ps.executeUpdate();//注意这里不是Query
            System.out.println(count);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
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
