
/*
	将连接数据库的所有信息放到到属性配置文件.properties文件中
*/
import java.sql.*;


public class JDBCTest03{
	
	public static void main(String[] args){
		
		//使用资源绑定器绑定属性配置文件
		ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
		String driver = bundle.getString("driver");
		String url = bundle.getString("url");
		String user = bundle.getString("user");
		String password = bundle.getString("password");
		
		Connection conn = null;
		Statement stmt = null;
		
		try{
			
			//注册驱动
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Class.forName(driver);
			
			//获取连接
			conn = DriverManager.getConnection(url,user,password);
			
			//获取(创建)数据库操作对象
			stmt = conn.createStatement();
			
			//执行删除的sql语句
			int count = stmt.executeUpdate("delete from emp2 where empno=7796");
			System.out.println(count == 1?"删除成功":"删除失败");
			
		}catch(Exception e){
			e.printStackTrace;
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