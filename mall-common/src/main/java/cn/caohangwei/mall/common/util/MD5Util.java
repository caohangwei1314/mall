package cn.caohangwei.mall.common.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Md5 Util.
 *
 * @author PinuoC
 */
public class MD5Util {

    private static final String salt = "jealous";

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    public static String inputPassFromPass(String src) {
        return md5("" + salt.charAt(3) + salt.charAt(1) + salt.charAt(2) + src + salt.charAt(0) + salt.charAt(4));
    }

    public static String fromPassDBPass(String src,String salt) {
        return md5("" + salt.charAt(3) + salt.charAt(1) + salt.charAt(2) + src + salt.charAt(4) + src.charAt(6));
    }

    public static String inputPassDBPass(String src,String dbSalt){
        String fromPass = inputPassFromPass(src);
        String DBPass = fromPassDBPass(fromPass,dbSalt);
        return DBPass;
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.inputPassDBPass("asd88751685","personality"));
    }

}
