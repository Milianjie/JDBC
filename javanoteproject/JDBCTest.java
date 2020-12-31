
/*
	JDBC编程六步
*/
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statment;

public class JDBCTest{
	
	public static void main(String[] args){
		
		Connection conn = null;
		Statment stmt = null;
		
		try{
		//注册驱动
		/*
			static void registerDriver(Driver driver) 
			向 DriverManager 注册给定驱动程序。 
			Driver在java.sql包中是一个接口，没法new对象，所以只能找其实现类new对象作为Driver类对象，而各数据库品牌的驱动中就有JDBC各接口的实现类
			这里用的是MySQL驱动，其Driver实现类是放在com.mysql.jdbc.Driver,所以下面得new带完整类名的Driver类对象
		*/
		Driver driver = new com.mysql.jdbc.Driver();//多态--要导包
		DriverManager.registerDriver(driver);//--要导包
		
		//获取连接
		/*
		public static Connection getConnection(String url,
                                       String user,
                                       String password)
				throws SQLException试图建立到给定数据库 URL 的连接。DriverManager 试图从已注册的 JDBC 驱动程序集中选择一个适当的驱动程序。
					该方法返回的是Connection接口类的连接对象
				url：统一资源定位符（网络中某个资源的绝对路径）
				包括：
					协议
					IP
					port
					资源名
				https://www.baidu.com/这个就是url
				http://182.61.200.7:80/index.html
				http://	通信协议
				182.61.200.7 服务器IP地址
				80 服务器上软件的端口号
				index.html
				
				什么是通信协议？有什么用
					通信协议就是通信之前就提前定好的数据传输格式
					数据包具体怎么传送数据，格式要提前定好
		*/
		String url = "jdbc:mysql://127.0.0.1:3306/mysqlstudy";
		//mysql:表示数据库是MySQL牌子的 
		//127.0.0.1：表示的是本机ip，也可以用localhost代替，想要连接其他电脑就得填其他pc的IP地址 
		//3306：表示的是端口号，每个软件都有一个端口号，我们当前所用的MySQL数据库管理系统软件的端口号就是3306
		//mysqlstudy：表示的是数据库名称
		String user = "root";
		String password = "rong195302";
		conn = DriverManager.getConnection(url,user,password);//--要导包
		System.out.println("数据库对象" + conn);
		
		//获取数据库操作对象
		/*Connection接口中有这样一个方法
			 Statement createStatement() 
          创建一个 Statement 对象来将 SQL 语句发送到数据库。 
		*/
		stmt = conn.createStatement();//--要导包
		
		//执行sql语句
		/*
			下面这个Statment中的方法是专门执行DML语句的：
				int executeUpdate(String sql) 
				执行给定 SQL 语句，该语句可能为 INSERT、UPDATE 或 DELETE 语句，或者不返回任何内容的 SQL 语句（如 SQL DDL 语句）
				返回一个int值，该值是影响数据库中的记录条数
		*/
		String sql1 = "insert into dept(deptno,dname,locattion) values(50,'人事部','北京');
		int count = stmt.executeUpdate(sql1);
		System.out.println(count==1?"插入成功":"插入失败");
		
		//处理查询结果集
		
		}catch(SQLException e){//--要导包
			e.printStackTrace();
		}finally{
			//释放资源
			//在finally语句块中关闭资源，可以保证资源一定会释放
			//要遵循从小到大依次关闭，且分别对其进行try/catch
			try{
				if(stmt != null){
				stmt.close();
			}
			}catch(SQLException e){
				e.printStackTrace;
			}
			try{
				if(conn != null){
				stmt.close();
			}
			}catch(SQLException e){
				e.printStackTrace;
			}
			
			
		}
	
		
	}
	
}