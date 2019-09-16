package cn.caohangwei.mall.common.util;

import com.alibaba.fastjson.JSON;

public class JSONUtil {

    public static <T> String beanToString(T value){
        return JSON.toJSONString(value);
    }

    public static <T> T stringToBean(String value,Class<T> clazz){
        if(null == value || "".equals(value) || null == clazz){
            return null;
        }
        if(String.class == clazz){
            return (T) value;
        } else if(boolean.class == clazz || Boolean.class == clazz){
            return (T) Boolean.valueOf(value);
        }else if(byte.class == clazz || Byte.class == clazz){
            return (T) Byte.valueOf(value);
        }else if(short.class == clazz || Short.class == clazz){
            return (T) Short.valueOf(value);
        }else if(char.class == clazz || Character.class == clazz){
            return (T) Character.valueOf(value.charAt(0));
        }else if(int.class == clazz || Integer.class == clazz){
            return (T) Integer.valueOf(value);
        }else if(float.class == clazz || Float.class == clazz){
            return (T) Float.valueOf(value);
        }else if(long.class == clazz || Long.class == clazz){
            return (T) Long.valueOf(value);
        }else if(double.class == clazz || Double.class == clazz){
            return (T) Double.valueOf(value);
        }else {
            return JSON.toJavaObject(JSON.parseObject(value),clazz);
        }
    }
}
