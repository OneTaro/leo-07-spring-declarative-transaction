package com.spring.declarative.init;

import com.spring.declarative.exception.RollbackException;
import com.spring.declarative.service.IFooService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @ClassName DeclarativeTransaction
 * @Description
 * @Author wangss
 * @date 2020.03.23 23:14
 * @Version 1.0
 */
@Slf4j
@Component
public class DeclarativeTransaction implements CommandLineRunner {

    @Autowired
    private IFooService fooService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        fooService.insertRecord();
        log.info("wangss {}", jdbcTemplate.queryForObject("SELECT count(1) FROM FOO WHERE BAR = 'wangss'", Long.class));

        try {
            fooService.insertThenRollback();
        } catch (RollbackException e) {
            log.info("JJJJ {}", jdbcTemplate.queryForObject("SELECT count(1) FROM FOO WHERE BAR = 'JJJJ'", Long.class));
        }

        // 这里的返回结果和上边不一样，做一个解释：insertThenRollback方法上有事务注解，在程序加载以后会生成代理方法进行增强，所以会回滚
        // invokeInsertThenRollback中调用insertThenRollback方法，是调用的类中的方法，而不是增强的代理类中的方法，所以不会回滚
        try {
            fooService.invokeInsertThenRollback();
        } catch (RollbackException e) {
            log.info("JJJJ {}", jdbcTemplate.queryForObject("SELECT count(1) FROM FOO WHERE BAR = 'JJJJ'", Long.class));
        }
    }

}
