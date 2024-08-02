package com.atguigu.senior;

import com.atguigu.senior.util.JDBCUtil;
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
}
