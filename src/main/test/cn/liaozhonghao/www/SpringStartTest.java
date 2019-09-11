package cn.liaozhonghao.www;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SpringStartTest {

    @Resource
    SpringStart springStart;


    @Test
    public void sayHello() {
        //调用sayHello方法
        springStart.sayHello();
    }
}