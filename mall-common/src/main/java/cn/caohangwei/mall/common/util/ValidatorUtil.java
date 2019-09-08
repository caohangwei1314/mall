package cn.caohangwei.mall.common.util;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {

    private static final Pattern pattern = Pattern.compile("^1[3456789]\\d{9}$");

    public static boolean isPhone(String str){
        if(StringUtils.isEmpty(str)){
            return false;
        }
        Matcher m = pattern.matcher(str);
        return m.matches();
    }

}
