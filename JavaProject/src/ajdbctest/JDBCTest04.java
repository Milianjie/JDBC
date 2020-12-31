package ajdbctest;

import java.io.FileFilter;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
    实现功能：
        1、需求：模拟用户登录功能的实现
        2、业务描述：
            程序运行，提供一个可以让用户输入用户名和密码的入口
            用户输入用户名会让密码之后，提交信息，java程序收集信息
            Java程序连接数据库验证用户名和密码
            合法：显示登陆成功
            不合法：显示登陆失败
        3、在实际开发中，表的设计使用专业的建模工具，这里安装使用的建模工具：PowerDesigner
            使用PD工具来进行数据库表的设计(具体参见user-login.sql脚本）
        4、下面的验证方法是自己写的，是先把表中的所有用户都查询出来，然后进行处理查询结果集遍历与用户输入信息进行if和equals
            比较，该方法可能效率没这么高，因为要把表中的所有数据查出来，还有if语句的原因
 */
public class JDBCTest04 {

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
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");

            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlstudy","root","rong195302");

            //获取（创建）数据库操作对象
            stmt = conn.createStatement();

            //执行sql查询语句，返回的是查询结果集
            rs = stmt.executeQuery("select * from t_user");

            //处理查询结果集
            while (rs.next()){
                if (userInfo.get("userName").equals(rs.getString("loginName")) || userInfo.get("userPwd").equals(rs.getString("loginPwd"))){
                    return true;
                }
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
