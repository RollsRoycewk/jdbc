package com.atguigu.advanced;

import com.atguigu.advanced.pojo.Employee;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: JDBCAdvanced
 * Package: com.atguigu.advanced
 * Description:
 *
 * @Author wk
 * @Create 2024-08-01 20:39
 * @Version 1.0
 */
public class JDBCAdvanced {
    @Test
    public void testORM() throws SQLException {
        //1.注册驱动 （可以省略）

        //2.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "abc123");

        //3.预编译SQL语句得到PreparedStatement对象
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT emp_id,emp_name,emp_salary,emp_age FROM t_emp WHERE emp_id= ?");

        preparedStatement.setInt(1,1);

        ResultSet resultSet = preparedStatement.executeQuery();
        Employee employee = null;

        if (resultSet.next()){
            employee = new Employee();
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");

            //为对象的属性赋值
            employee.setEmpId(empId);
            employee.setEmpName(empName);
            employee.setEmpSalary(empSalary);
            employee.setEmpAge(empAge);
        }
        System.out.println(employee);

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testORMList()  throws SQLException {
        //1.注册驱动 （可以省略）

        //2.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "abc123");

        //3.预编译SQL语句得到PreparedStatement对象
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT emp_id,emp_name,emp_salary,emp_age FROM t_emp");

        ResultSet resultSet = preparedStatement.executeQuery();

        Employee employee = null;
        List<Employee> employeeList = new ArrayList<>();

        while  (resultSet.next()){
            employee = new Employee();
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");

            //为对象的属性赋值
            employee.setEmpId(empId);
            employee.setEmpName(empName);
            employee.setEmpSalary(empSalary);
            employee.setEmpAge(empAge);
            //将每次循环封装的一行数据的对象存储在集合里
            employeeList.add(employee);
        }

        //5.处理结果：遍历集合！
        for (Employee emp : employeeList){
            System.out.println(emp);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testReturnPK() throws Exception {
        //1.注册驱动 （可以省略）

        //2.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "abc@1234");

        // 预编译SQL语句，告知preparedStatement，返回新增数据的主键列的值
        String sql = "INSERT INTO t_emp(emp_name,emp_salary,emp_age) VALUES(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        // 创建对象，将对象的属性值，填充在?占位符上 （ORM）
        Employee employee = new Employee(null, "jack", 123.45, 29);
        preparedStatement.setString(1, employee.getEmpName());
        preparedStatement.setDouble(2,employee.getEmpSalary());
        preparedStatement.setInt(3,employee.getEmpAge());

        //执行SQL，并获取返回的结果
        int result = preparedStatement.executeUpdate();
        ResultSet resultSet = null;

        //处理结果
        if (result > 0){
            System.out.println("成功！");

            //获取当前新增数据的主键列，回显到Java中employee对象的empId属性上。
            //返回的主键值，是一个单行单列的结果存储在ResultSet里
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                int empId = resultSet.getInt(1);
                employee.setEmpId(empId);
            }

            System.out.println(employee);
        }else {
            System.out.println("失败！");
        }

        //释放资源
        if (resultSet != null){
            resultSet.close();
        }
        preparedStatement.close();
        connection.close();

    }

    @Test
    public void testMoreInsert() throws Exception {
        //1.注册驱动
        //  Class.forName("com.mysql.cj.jdbc.Driver");

        //2.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "abc@1234");

        // 3.编写SQL语句
        String sql = "INSERT INTO t_emp(emp_name,emp_salary,emp_age) VALUES(?,?,?)";

        //4.创建预编译的PreparedStatement，传入SQL语句
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //获取当前行代码执行的时间。毫秒值
        long start = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            //5.为占位符赋值
            preparedStatement.setString(1,"marry" + i);
            preparedStatement.setDouble(2,100.00 + i);
            preparedStatement.setInt(3,20 + i);

            preparedStatement.executeUpdate();
        }

        long end = System.currentTimeMillis();
        System.out.println("消耗时间："+(end - start));

        preparedStatement.close();
        connection.close();
    }

    @Test
    public  void testBatch() throws Exception{
        //1.注册驱动
        //  Class.forName("com.mysql.cj.jdbc.Driver");

        //2.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu?rewriteBatchedStatements=true", "root", "abc@1234");

        //3.编写SQL语句
        /*
            注意：1、必须在连接数据库的URL后面追加?rewriteBatchedStatements=true，允许批量操作
                2、新增SQL必须用values。且语句最后不要追加;结束
                3、调用addBatch()方法，将SQL语句进行批量添加操作
                4、统一执行批量操作，调用executeBatch()
         */
        String sql = "insert into t_emp (emp_name,emp_salary,emp_age) values (?,?,?)";

        //4.创建预编译的PreparedStatement，传入SQL语句
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //获取当前行代码执行的时间。毫秒值
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            preparedStatement.setString(1, "marry"+i);
            preparedStatement.setDouble(2, 100.0+i);
            preparedStatement.setInt(3, 20+i);
            preparedStatement.addBatch();
        }

        //执行批量操作
        preparedStatement.executeBatch();

        long end = System.currentTimeMillis();
        System.out.println("消耗时间："+(end - start));

        preparedStatement.close();
        connection.close();
    }
}
