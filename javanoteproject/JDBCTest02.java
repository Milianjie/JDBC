
/*
	注册驱动的另外一种方式
*/
import java.sql.*;

public class JDBCTest02{
	
	public static void main(String[] args){
		
		Connection conn = null;
		Statement stmt = null;
		
		try{
			
			//注册驱动的第一种方式
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			
			//注册驱动的另外一种方式，类加载的方式，在MySQL的驱动中，其Driver实现类有一静态代码块，完成注册驱动的任务
			//我们想要执行静态代码块而不执行com.mysql.jdbc.Driver中的其他东西，需要只执行类加载，用反射机制
			Class.forName("com.mysql.jdbc.Driver");//该方式常用，因为参数是字符串，字符串可以写到xxx.properties文件中
			//上面方式不需要返回值，因为我们只需要其类加载的动作
			
			//获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlstudy","root","rong195302");
			
			//获取(创建)数据库操作对象
			stmt = conn.createStatement();
			
			//执行删除的sql语句
			int count = stmt.executeUpdate("delete from emp2 where empno=7796);
			System.out.println(count == 1?"删除成功":"删除失败");
			
		}catch(SQLException e){
			e.printStackTrace;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			//关闭资源
			try{
				if(stmt != null){
					stmt.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			try{
				if(conn != null){
				conn.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
	}
		
}