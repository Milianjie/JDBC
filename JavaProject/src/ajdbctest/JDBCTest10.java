package ajdbctest;

import ajdbctest.utils.DBUtil;
import com.sun.source.tree.ParenthesizedTree;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  1、在本类中测试DBUtil工具类的使用
 *  2、如何实现模糊查询
 */
public class JDBCTest10 {

    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //注册驱动和获取数据库连接对象
            conn = DBUtil.getConnection();

            //获取预编译数据库操作对象
            //查询第二各字母为A的名字
            /*错误写法
            String sql = "select ename from emp where ename like '_?%'";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"A");
            */
            String sql = "select ename from emp where ename like ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"_A%");

            //执行sql语句
            rs = ps.executeQuery();

            //处理查询结果集
            while(rs.next()){
                System.out.println(rs.getString("ename"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }

    }

}
