
/*
	处理查询结果集
*/
import java.sql.*;
public class JDBCSelect{
	
	public static void main(String[] args){
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			
			//注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			
			//获取连接
			conn = DriverManager.getConnection("mysql:jdbc://localhost:3306/mysqlstudy","root","rong195302");
			
			//获取数据库操作对象
			stmt = conn.createStatement();
			
			//执行sql语句（查询语句）
			String sql = "select ename,sal,emptno from emp";
			rs = executeQuery(sql);
			
			//处理查询结果集
			/*
				结果集接口ResultSet中有这样一个方法
				boolean next()
						throws SQLException
				将光标从当前位置向前移一行。ResultSet 光标最初位于第一行之前；第一次调用 next 方法使第一行成为当前行；第二次调用使第二行成为当前行，依此类推。 
				当调用 next 方法返回 false 时，光标位于最后一行的后面。任何要求当前行的 ResultSet 方法调用将导致抛出 SQLException。
				如果结果集的类型是 TYPE_FORWARD_ONLY，则其 JDBC 驱动程序实现对后续 next 调用是返回 false 还是抛出 SQLException 将由供应商指定。
				
				我们可以发现该方法集合中的迭代器相像，只不过集合中的对象数据只是一个，而该结果集中是每个对象一行数据，而且一行中每列分为一个数据，所以遍历某个数据的方式与集合的不相同
				 获取某列的具体数据方法：
				 String getString(int columnIndex) 
				以 Java 编程语言中 String 的形式获取此 ResultSet 对象的当前行中指定列的值。
					当然也有以基本数据类型的形式取出数据的方法:
					int getInt();
					double getDouble();
					...
			*/
			while(rs.next()){
				
				//String ename = rs.getString(1);//注意到第一列下标是1，不是0，这与数组不一样,但是这样使程序不健壮，里面放具体列名
				//String sal = rs.getString(2);
				//String empno = rs.getString(3);
				
				//里面放具体列名,但这里是查询语句中查询结果集的列名，不是表中的列名，
				//查询语句中可以重命名查询结果表中的列名，这里要与查询语句中的一致，不然报错
				String ename = rs.getString("ename");
				String sal = rs.getString("sal");
				String empno = rs.getString("empno");
				System.out.println(ename+","+sal+","+empno);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs != null){
					rs.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
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