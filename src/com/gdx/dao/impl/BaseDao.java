package com.gdx.dao.impl;

import com.gdx.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


/**
 * @author 郭昊晨
 * @version 1.0
 * 2022/1/17 - 17:06
 */
public abstract class BaseDao {
    //使用dbutils操作数据库

   private QueryRunner queryRunner=new QueryRunner();
    /**
     *
     * @param sql
     * @param args
     * @return 返回-1表示执行失败，返回其他表示影响的行数
     */
    public int update(String sql,Object...args){
        Connection connection= JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection,sql,args); //返回影响的行数
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回一个javaBean对象
     * @param type:返回对象的类型
     * @param sql:执行sql语句
     * @param args:sql语句中对应的参数
     * @param <T>:返回对象的类型的泛型
     * @return
     */
    public <T> T queryForOne(Class<T> type,String sql,Object...args){
        Connection con= JdbcUtils.getConnection();

        try {
            return queryRunner.query(con,sql,new BeanHandler<T>(type),args);
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回多个javaBean对象
     * @param type:返回对象的类型
     * @param sql:执行sql语句
     * @param args:sql语句中对应的参数
     * @param <T>:返回对象的类型的泛型
     * @return
     */
    public <T> List<T> queryForList(Class<T> type,String sql,Object...args){
        Connection con= JdbcUtils.getConnection();

        try {
            return queryRunner.query(con,sql,new BeanListHandler<T>(type),args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Object querySingleValue(String sql,Object...args){
            Connection con=JdbcUtils.getConnection();
        try {
            return queryRunner.query(con,sql,new ScalarHandler(),args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
