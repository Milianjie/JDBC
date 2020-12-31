package ajdbctest;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
    1、解决SQL注入问题
        使用户提供的用户信息不参与sql语句的编译就可以解决了
        如何实现：使用java.sql.PreparedStatement
        java.sql.PreparedStatement接口继承了java.sql.Statement接口
        java.sql.Statement是数据库操作对象
        java.sql.PreparedStatement是属于预编译的数据库操作对象
        原理：先对给定的sql框架进行预编译，然后在传所需要的条件值

     2、对比Statement和PreparedStatement
        -前者存在SQL注入问题，后者解决了SQL注入问题
        -一般情况下，我们在Windows命令窗口里执行一条SQL语句，第一次执行需要编译，但第二次还是这条不变的SQL语句时不会编译直接执行
        -所以前者是编译一次执行一次，后者由于SQL语句框架不变，编译一次执行N次
        -后者会在编译阶段做安全检查
      综上所述：PreparedStatement使用较多，Statement使用较少

      3、什么情况下使用Statement？
        业务方面要求必须支持SQL注入
 */
public class JDBCTest06 {

    public static void main(String[] args) {

        //初始化用户登录界面方法，让用户输入用户名和密码
        Map<String,String> userLoginInfo = initUI();

        //连接数据库，验证用户输入信息，即登录方法
        boolean userLogin = userLogin(userLoginInfo);

        System.out.println(userLogin?"登录成功!":"登陆失败!");

    }

    /**
     * 连接数据库，验证用户信息的方法
     * @return  返回true，登陆成功；返回false。登录失败
     */
    private static boolean userLogin(Map<String,String> userInfo) {

        Connection conn = null;

        //Statement stmt = null;
        PreparedStatement ps = null;

        ResultSet rs = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");

            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlstudy?useSSL=false","root","rong195302");

            //获取（创建）预编译的数据库操作对象
            //stmt = conn.createStatement();
            //SQL语句的框架。其中?表示一个占位符，一个?将来接收一个"值"，里面的问号不能用单引号括起来，因为你不知道将来传进的值一定是字符串类型
            String sql = "select * from t_user where loginName= ? and loginPwd= ?";
            //程序执行到下面语句，发送sql语句框子给DBMS，DBMS进行sql语句的预先编译
            ps = conn.prepareStatement(sql);
            //给占位符?传值（第一个?下标是1，第二个?下标是2，JDBC中所有的下标都是从1开始）
            //传值方法的set后面跟传入值的类型，这里是字符串 所以是setString
            ps.setString(1,userInfo.get("userName"));
            ps.setString(2,userInfo.get("userPwd"));

            //执行sql查询语句，返回的是查询结果集
            //rs = stmt.executeQuery(sql);
            ps.executeQuery();//sql语句已经放进预编译数据库操作对象里了，所以不在传sql语句参数

            //处理查询结果集
            while (rs.next()){
                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
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
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 初始化用户界面
     * @return  一个map数组，用户输入的用户名和密码
     */
    private static Map<String, String> initUI() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String userName = scanner.nextLine();//该方法表示一次读一行
        System.out.println("请输入密码：");
        String userPwd = scanner.nextLine();

        Map<String,String> userInfo = new HashMap<>();
        userInfo.put("userName",userName);
        userInfo.put("userPwd",userPwd);

        return userInfo;
    }

}
