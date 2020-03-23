package com.spring.declarative.service.impl;

import com.spring.declarative.exception.RollbackException;
import com.spring.declarative.service.IFooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName FooServiceImpl
 * @Description 声明式事务
 * @Author wangss
 * @date 2020.03.23 23:06
 * @Version 1.0
 */
@Service
public class FooServiceImpl implements IFooService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public void insertRecord() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ( 'wangss' )");
    }

    @Transactional(rollbackFor = RollbackException.class)
    @Override
    public void insertThenRollback() throws RollbackException {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ( 'JJJJ' )");
        throw new RollbackException();
    }

    @Override
    public void invokeInsertThenRollback() throws RollbackException {
        insertThenRollback();
    }
}
