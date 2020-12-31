### 1、JDBC是什么

##### Java DataBase Connectivity（Java语言连接数据库）

### 2、JDBC的本质

```java
JDBC是SUN公司制定的一套接口（interface）
接口，有实现者和调用者
一般来讲，面向接口调用或者面向接口写实现类，称之为面向接口编程，也称面向抽象编程
//为什么要面向接口或者抽象编程?
    为了解耦合：降低程序的耦合度，提高其扩展性
例如多态机制就是面向抽象编程，谨记：不要面向具体编程
    建议：
    Animal a = new Cat()
    Animal b = new Dog()
    //喂养的方法
    public void feed(Animal a){}//父类型的引用指向子类型的对象，面向父类编程

	不建议：
    Cat a = new Cat()
    Dog b = new Dog()
        
//为什么SUN公司要制定一套JDBC接口
       因为不同数据库底层实现原理不同，Oracle数据库有自己的实现原理，MySQL数据库也有自己独特的原理
MS SQL server同样有...
       为了用Java语言连接数据库，JDBC接口就是这些数据库共同遵守的规范，各自的数据库厂家编写了一套各自对JDBC接口的实现类，数据库厂家就是JDBC的实现者，而我们也面向JDBC接口写代码，调用实现类，是接口的调用者。这样我们在用java连接不同数据库时免去了为不同数据库都写一整套的连接方式。
 
//我们写的代码，是class文件，存储位置是知道的；JDBC接口中的class文件存储在java.sql软件包中，从API文档可以看到里面有许多的接口；而数据库厂家写的实现类需要我们在其数据库官网上下载对应版本数据库的jar包，这些jar包里面对JDBC接口的实现类的class文件，这个jar包也称之为驱动，只是一个名称，有MySQL驱动，Oracle驱动等等
        
```

![](G:\BaiduNetdiskDownload\课堂笔记\课堂笔记\3.png)

### 3、JDBC开发前的准备工作

先从官网下载	对应的驱动jar包，然后将其配置到环境变量classpath中

.;F:\Git_Repositories\JDBC\resources\mysql-connector-java-5.1.49\mysql-connector-java-5.1.49-bin.jar

以上的配置是针对文本编辑器的方式开发，使用IDEA工具不需要配置以上的环境变量。

IDEA有自己的配置方式

### 3、JDBC编程六步

##### 第一步：注册驱动。作用：告诉java程序，即将要连接的是哪个品牌的数据库

##### 第二步：获取连接。将JVM和数据库进程之间的通道打开，相互通信，这是进程之间的通信，非常重量级，用完之后要关闭

##### 第三步：获取数据库操作对象。该对象是专门执行sql语句的，没有它就没法执行sql语句

##### 第四步：执行sql语句。DQL，DML，...

##### 第五步：处理查询结果集。只有当上一步执行的是select语句才有这步

##### 第六步：释放资源。使用完资源一定要关闭资源。

### 5、使用IDEA配置JDBC驱动

在需要驱动的模块上点击右键，找到Open Module settings点进去

![](F:\Git_Repositories\JDBC\截图\idea配置jdbc导包\idea配置jdbc1.png)

点进去后找到Libraries，点击

![](F:\Git_Repositories\JDBC\截图\idea配置jdbc导包\idea配置jdbc2.png)

然后点击右边的加号+，选择第一个java，选择要导入的jar包，即驱动

![](F:\Git_Repositories\JDBC\截图\idea配置jdbc导包\idea配置jdbc3.png)

查看导入了上面jar包，可以点开模块下面的External Libraries

![](F:\Git_Repositories\JDBC\截图\idea配置jdbc导包\查看导入的jar包.png)

### 5、简单使用PowerDesigner工具设计表

说明：使用PD工具是在设计表阶段时使用的工具，当我们在开发阶段时使用的是Navicate工具

打开PD软件，选择新建模块

![](F:\Git_Repositories\JDBC\截图\简单使用PD工具\PD使用1.png)

然后点击Model types，选择Physical Data Model

![](F:\Git_Repositories\JDBC\截图\简单使用PD工具\使用PD2.png)

然后在Model Name填写项目名字，DBMS处选择对应的数据库以及版本，点击ok，这样就创建好物理数据模型了

![](F:\Git_Repositories\JDBC\截图\简单使用PD工具\使用PD3.png)

![](F:\Git_Repositories\JDBC\截图\简单使用PD工具\使用PD4.png)

上图中中间的是一面画板，鼠标放上去，按住CTRL键，滚动鼠标滑轮，可放大缩小，每一个小格子里都可以放很多张表

点击右边Physical Diagram下面table图案，然后在小方格内点击创建一张表，再点击Standard下面的第一个图标按上面方法放大缩小表

![](F:\Git_Repositories\JDBC\截图\简单使用PD工具\使用PD5.png)

![](F:\Git_Repositories\JDBC\截图\简单使用PD工具\使用PD6.png)

![](F:\Git_Repositories\JDBC\截图\简单使用PD工具\使用PD7.png)

双击表，编辑表，此时设置General，其中Name是该表的注释名称，Code才是这个表在数据库中的真正表名

![](F:\Git_Repositories\JDBC\截图\简单使用PD工具\使用PD8.png)

然后编辑Colunms列字段，和上面一样，Name是注释名称，方便看懂，Code才是真正字段名，其余的都可以看懂

![](F:\Git_Repositories\JDBC\截图\简单使用PD工具\使用PD9.png)

Mandatory勾上表示不能为null。点击应用，ok

![](F:\Git_Repositories\JDBC\截图\简单使用PD工具\使用PD10.png)

双击表，然后点击Preview可查看建表sql语句，点击保存图标可选择保存为sql脚本文件，后缀要自己改.sql

![](F:\Git_Repositories\JDBC\截图\简单使用PD工具\使用PD11.png)

主界面CTRL+s保存该表，后缀为.pdm

![](F:\Git_Repositories\JDBC\截图\简单使用PD工具\使用PD12.png)

关闭软件，不保存工作空间

想要再次打开时，直接点击保存的pdm文件，找不到表的话直接点左边列表中的表名右键第一个

记事本打开保存的sql脚本，可以往里面添加需求语句，如给主键自增，或插入数据的sql语句，此时默认保存时的编码方式需要注意是UTF-8还是ANSI（简体中文）

