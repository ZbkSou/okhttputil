package com.ihaveu.util;

/**
 * Created by ZBK on 2018-03-25.
 */

public class Utils {

    public static boolean isExist(String className,ClassLoader loader){

        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

    }
}
