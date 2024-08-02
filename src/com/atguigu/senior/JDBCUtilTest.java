package com.atguigu.senior;

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
}
