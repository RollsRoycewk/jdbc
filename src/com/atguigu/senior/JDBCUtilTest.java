package com.atguigu.senior;


import com.atguigu.senior.dao.impl.BankDaoImpl;
import com.atguigu.senior.dao.impl.EmployeeDaoImpl;
import com.atguigu.senior.util.JDBCUtil;
import com.atguigu.senior.util.JDBCUtilV2;
import org.junit.Test;

import java.sql.Connection;

/**
 * ClassName: JDBCUtilTest
 * Package: com.atguigu.senior
 * Description:
 *
 * @Author wk
 * @Create 2024/8/2 22:43
 * @Version 1.0
 */
public class JDBCUtilTest {
    @Test
    public void testGetConnection() {
        Connection connection = JDBCUtil.getConnection();
        System.out.println(connection);

        //CRUD
        JDBCUtil.release(connection);
    }

    @Test
    public void testJDBCV2() {
        /*

        Connection connection = JDBCUtil.getConnection();
        Connection connection1 = JDBCUtil.getConnection();
        Connection connection2 = JDBCUtil.getConnection();

        不同的三个连接
        System.out.println(connection);
        System.out.println(connection1);
        System.out.println(connection2);
        */

        // 是同一个连接
        Connection connection1 = JDBCUtilV2.getConnection();
        Connection connection2 = JDBCUtilV2.getConnection();
        Connection connection3 = JDBCUtilV2.getConnection();
        System.out.println(connection1);
        System.out.println(connection2);
        System.out.println(connection3);
    }

    @Test
    public void testEmployeeDao() {
        //1.创建DAO实现类对象
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();

/*        //2.调用查询所有方法
        List<Employee> employeeList = employeeDao.selectAll();

        //3.处理结果
        for (Employee employee : employeeList) {
            System.out.println("employee : " + employee);
        }*/

        //调用根据id查询单个员工方法
//        Employee employee = employeeDao.selectByEmpId(1);
//        System.out.println("employee:" + employee);

        //调用添加员工的方法
//        Employee employee = new Employee(null, "tom", 300.65, 38);
//        int insert = employeeDao.insert(employee);
//        System.out.println("insert = " + insert);

//        Employee employee = new Employee(20007, "tom", 656.65, 38);
//        int update = employeeDao.update(employee);
//        System.out.println("update:" + update);

        int delete = employeeDao.delete(20007);
        System.out.println("delete:" + delete);

    }

    @Test
    public void testTransaction() {
        BankDaoImpl bankDao = new BankDaoImpl();
        Connection connection = null;

        try {
            //1.获取连接，将连接的事务提交改为手动提交
            connection = JDBCUtilV2.getConnection();
            //开启事务，当前连接的自动提交关闭。改为手动提交！
            connection.setAutoCommit(false);

            //2.操作减钱
            bankDao.subMoney(1, 100);

//            int i = 10 / 0;

            //3.操作加钱
            bankDao.addMoney(2, 100);

            //4.前置的多次dao操作，没有异常，提交事务！
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            JDBCUtilV2.release();
        }


    }
}
