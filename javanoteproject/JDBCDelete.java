
import java.sql.*;

public class JDBCDelete{
	
	public static void main(String[] args){
		
		Connection conn = null;
		Statement stmt = null;
		
		try{
			
			//注册驱动
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			
			//获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlstudy","root","rong195302");
			
			//获取(创建)数据库操作对象
			stmt = conn.createStatement();
			
			//执行删除的sql语句
			int count = stmt.executeUpdate("delete from emp2 where empno=7796);
			System.out.println(count == 1?"删除成功":"删除失败");
			
		}catch(SQLException e){
			e.printStackTrace;
		}finally{
			//关闭资源
			try{
				if(stmt != null){
					stmt.close();
				}
			}catch(SQLException e){
				e.printStackTrace;
			}
			try{
				if(conn != null){
				conn.close();
				}
			}catch(SQLException e){
				e.printStackTrace;
			}
		}
		
	}
		
}