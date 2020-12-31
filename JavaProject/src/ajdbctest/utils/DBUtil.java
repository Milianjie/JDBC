package ajdbctest.utils;

import java.sql.*;

/**
    JDBC工具类，将JDBC编程的一些步骤封装进一个工具类中，简化每一次的JDBC操作
 */
public class DBUtil {

    /**
     * 不能每次调用获取连接对象的方法都注册驱动，所以将注册驱动的语句放到工具类中的静态语句块中
     * 在第一次类名.工具中的方法时，加载工具类，只加载一次，所以静态语句块在加载该类时执行，只执行一次
     */
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * 工具类中的构造方法都是私有的
     * 因为工具类中的方法都是静态的，直接类名.调用，不需要new对象
     * 为了防止别人new工具类的对象直接将其构造方法私有化
     */
    private DBUtil(){}

    /**
     * 一个获取连接的方法
     * @return  返回一个连接到某个数据库的Connection对象
     * @throws SQLException 这里上抛异常，不做try catch处理，该方法是放进try语句块中执行的
     */
    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlstudy?useSSL=false","root","rong195302");

    }

    /**
     * 关闭资源的方法，这里不需要抛出异常，因为不是在try catch中执行的
     * @param conn  数据库连接对象
     * @param ps    数据库操作对象或者是数据库预编译操作对象
     * @param rs    结果集对象
     */
    public static void close(Connection conn, Statement ps, ResultSet rs){

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn!= null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
