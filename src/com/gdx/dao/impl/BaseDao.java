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
 * @author ��껳�
 * @version 1.0
 * 2022/1/17 - 17:06
 */
public abstract class BaseDao {
    //ʹ��dbutils�������ݿ�

   private QueryRunner queryRunner=new QueryRunner();
    /**
     *
     * @param sql
     * @param args
     * @return ����-1��ʾִ��ʧ�ܣ�����������ʾӰ�������
     */
    public int update(String sql,Object...args){
        Connection connection= JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection,sql,args); //����Ӱ�������
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * ��ѯ����һ��javaBean����
     * @param type:���ض��������
     * @param sql:ִ��sql���
     * @param args:sql����ж�Ӧ�Ĳ���
     * @param <T>:���ض�������͵ķ���
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
     * ��ѯ���ض��javaBean����
     * @param type:���ض��������
     * @param sql:ִ��sql���
     * @param args:sql����ж�Ӧ�Ĳ���
     * @param <T>:���ض�������͵ķ���
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
