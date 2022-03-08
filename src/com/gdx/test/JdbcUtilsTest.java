package com.gdx.test;

import com.gdx.utils.JdbcUtils;
import org.junit.Test;


/**
 * @author ¹ùê»³¿
 * @version 1.0
 * 2022/1/16 - 21:51
 */
public class JdbcUtilsTest {
    @Test
    public void test1() {

        System.out.println(JdbcUtils.getConnection());
    }
}
