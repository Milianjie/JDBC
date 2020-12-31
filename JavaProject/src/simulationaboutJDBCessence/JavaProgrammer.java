package simulationaboutJDBCessence;

import java.util.ResourceBundle;

/*
    Java程序员角色。
    不需要关心具体的是哪个品牌的数据库，只需要面向JDBC接口写代码
    即面向接口编程，面向抽象编程，不要面向具体编程（这里不面向具体是哪个数据库编程）
 */
public class JavaProgrammer {

    public static void main(String[] args) throws Exception {

        //下面代码，要连接哪个数据库就创建哪个数据库对象
        //JDBC jdbc = new MySQL();
        JDBC jdbc = new Oracle();

        //有时为了连接不同的数据库，不能老是修改java程序的代码，利用反射机制创建对象
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String className = bundle.getString("className");
        Class c = Class.forName(className);
        //这里强转成接口类型，不强转成具体数据库类型
        JDBC jdbc1 = (JDBC)c.newInstance();

        //下面代码是面向接口调用方法，无论上面怎么new不一样的对象，都不需要修改
        jdbc.getConnection();
    }

}
