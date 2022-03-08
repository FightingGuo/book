package com.gdx.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


/**
 * @author 郭昊晨
 * @version 1.0
 * 2022/1/16 - 21:19
 */
public class JdbcUtils {

    /**
     * 获取数据库连接池中的连接
     * @return
     */

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns=new ThreadLocal<Connection>();

    static {
        try {
            Properties properties=new Properties();
            //读取jdbc.utils属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(inputStream);
            //创建数据库连接池
            dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection(){
        Connection conn = conns.get();//从ThreadLocal中取连接

        if (conn==null){  //第一次从ThreadLocal中取时 ThreadLocal为空
            try {
                conn=dataSource.getConnection(); //从数据库连接池中取
                conns.set(conn);  //保存到ThreadLocal对象中，
                conn.setAutoCommit(false); //设置数据库事务提交方式为手动提交


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事务并关闭连接
     */
    public static void commitAndClose() {
        Connection conn = conns.get();
        if (conn != null) { //如果不等于null，说明之前使用过连接，操作过数据库

            try {
                conn.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    conn.close(); //无论是否提交成功都要关闭连接
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
//使用完后一定要执行remove操作，否则就会报错。(因为Tomcat底层使用了线程池技术)
            conns.remove();
        }
    }

        /**
         * 回滚事务
         */
        public static void rollbackAndClose () {
            Connection conn = conns.get();
            if (conn != null) { //如果不等于null，说明之前使用过连接，操作过数据库

                try {
                    conn.rollback();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    try {
                        conn.close(); //无论是否回滚成功都要关闭连接
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
//使用完后一定要执行remove操作，否则就会报错。(因为Tomcat底层使用了线程池技术)
                conns.remove();
            }

        }
//    public static void colse(Connection connection){
//        if (connection!=null){
//            try {
//                connection.close();
//            } catch (Exception throwables) {
//                throwables.printStackTrace();
//            }
//        }




}
