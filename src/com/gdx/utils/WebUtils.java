package com.gdx.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * @author ��껳�
 * @version 1.0
 * 2022/2/1 - 18:21
 */
public class WebUtils {
    public static <T> T copyParamToBean(Map value, T t) {
        try {
            BeanUtils.populate(t, value); //�Ѳ���ע�뵽javaBean������
        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;
    }

    /**
     * ���ַ���ת����int���͵�����
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
