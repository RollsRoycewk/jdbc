package com.atguigu.base;

import java.sql.*;
import java.util.Scanner;

/**
 * ClassName: JDBCPrepared
 * Package: com.atguigu.base
 * Description:
 *
 * @Author wk
 * @Create 2024-08-01 18:49
 * @Version 1.0
 */
public class JDBCPrepared {
    public static void main(String[] args) throws Exception {
        //1.注册驱动 （可以省略）

        //2.获取连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "abc123");

        //3.获取执行SQL语句对象
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT emp_id,emp_name,emp_salary,emp_age FROM t_emp WHERE emp_name = ?");
        Statement statement = connection.createStatement();

        System.out.println("请输入员工姓名：");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        // ' OR '1'='1

        ///4.为?占位符复制，并执行SQL语句，接受返回的结果
        // 设置PreparedStatement的第一个参数（索引从1开始）
        // 1: 参数索引。PreparedStatement 的参数索引从 1 开始，即第一个 ? 的位置是索引 1。
        // name: 用户输入的员工姓名，它将被设置为 SQL 查询中的第一个参数。
        preparedStatement.setString(1,name);
        ResultSet resultSet = preparedStatement.executeQuery();

        //5.处理结果：遍历resultSet
        while (resultSet.next()){
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId+"\t"+empName+"\t"+empSalary+"\t"+empAge);
        }

        //6.释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }
}
