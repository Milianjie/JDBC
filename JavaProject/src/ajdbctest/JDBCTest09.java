package ajdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
    JDBC事务机制：
        JDBC中的事务是自动提交的，只要执行任意一条DML语句，就会自动提交一次。
        这是JDBC默认的事务行为。
        然而在实际的业务中，需要N条DML语句共同联合才能完成，必须保证它们这些DML语句
        同时成功或者同时失败

        下面演示关闭事务自动提交机制的转账演示事务
        drop table if exists t_acn;
        create table t_acn(
            account int,
            balance double(7,2)
        )
        select * from t_acn;
        上面的7表示7为有效数字，2表示保留的小数点数

        通过演示，可以发现，在执行了一条DML语句后
        当我们把一个异常放进去影响了后面的DML语句时，数据库中的数据并没有发生改变，实现了回滚
        然后把异常去掉，程序正常执行，数据库表中数据正常改变

        重点三行代码：
        conn.setAutoCommit();
        conn.commit();
        coon.rollback();
 */
public class JDBCTest09 {

    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");

            //获取连接(在获取连接后，用Connection接口中的setAutoCommit方法关闭自动提交
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlstudy?useSSL=false","root","rong195302");
            conn.setAutoCommit(false);//true表示开启自动提交，false表示关闭。这里开始事务

            //获取预编译数据库操作对象
            String sql1 = "update t_acn set balance = ? where account = ?";
            ps = conn.prepareStatement(sql1);
            //给?传值
            ps.setDouble(1,10000.00);
            ps.setInt(2,111);

            //执行sql
            int count = ps.executeUpdate();

//            //在这里设置一个一定会出现异常的语句，使下面的语句包括第二条DML语句无法成功执行
//            //来模拟多条DML语句完成一个事务当一条DML语句没有成功时的情形
//            //这里会出现空指针异常,下面的提交事务没法执行，在try这个空指针异常的catch语句块中编写回滚事务的代码
//            //使事务开始后做的操作回到事务开始前
//            String i = null;
//            i.toString();

            //
            String sql2 = "update t_acn set balance = ? where account = ?";
            ps = conn.prepareStatement(sql2);
            //给?传值
            ps.setDouble(1,10000.00);
            ps.setInt(2,222);

            //执行sql
            count += ps.executeUpdate();

            System.out.println(count==2?"转账成功":"转账失败");

            //程序能执行到这里说明以上程序没有异常，事务结束，手动提交数据
            conn.commit();

        } catch (NullPointerException e){
            //这里回滚（这里是为了演示事务失败结束而随便让其出的异常）
            if (conn == null) {
                try {
                    conn.rollback();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
          e.printStackTrace();
        }catch (ClassNotFoundException e) {
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
