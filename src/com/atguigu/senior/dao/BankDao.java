package com.atguigu.senior.dao;

/**
 * ClassName: BankDao
 * Package: com.atguigu.senior.dao
 * Description:
 *
 * @Author wk
 * @Create 2024/8/3 00:54
 * @Version 1.0
 */
public interface BankDao {
    int addMoney(Integer id, Integer money);

    int subMoney(Integer id, Integer money);
}
