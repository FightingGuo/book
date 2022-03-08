package com.gdx.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * @author 郭昊晨
 * @version 1.0
 * 2022/2/1 - 18:21
 */
public class WebUtils {
    public static <T> T copyParamToBean(Map value, T t) {
        try {
            BeanUtils.populate(t, value); //把参数注入到javaBean对象中
        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;
    }

    /**
     * 将字符串转换成int类型的数据
     * @param str
     * @param defaultValue
     * @return
     */
    public static int parseInt(String str,int defaultValue ){
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }
}
