package com.xandone.dog.wcapp.uitils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * author: xandone
 * created on: 2018/3/15 9:22
 */

public class MD5Utils {
    /**
     * md5加密(16位 小写)
     *
     * @param password
     * @return
     */
    public static String decode16(String password) {
        return decode32(password).substring(8, 24);
    }

    /**
     * md5加密(32位 小写)
     *
     * @param password
     * @return
     */
    public static String decode32(String password) {

        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                // System.out.println(str);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }

            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }

    }
}
