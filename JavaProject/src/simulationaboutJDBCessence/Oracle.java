package simulationaboutJDBCessence;

/*
    Oracle数据库厂家负责编写JDBC接口的实现类
 */
public class Oracle implements JDBC {
    @Override
    public void getConnection() {
        //具体这里的代码怎么写，于我吗Java程序员来说不用关心
        //这段代码涉及Oracle数据库的底层原理
        System.out.println("连接Oracle数据库成功");
    }
}
