package cn.caohangwei.mall.common.util;

import java.util.HashMap;
import java.util.ResourceBundle;

public class PropertiesUtil {

    private static HashMap<String, PropertiesUtil> configMap = new HashMap<String, PropertiesUtil>();

    private ResourceBundle resourceBundle;

    public PropertiesUtil(String name) {
        this.resourceBundle = ResourceBundle.getBundle(name);
    }

    public static PropertiesUtil getInstance(String name) {
        PropertiesUtil conf = configMap.get(name);
        if (null == conf) {
            synchronized (PropertiesUtil.class) {
                conf = configMap.get(name);
                if (null == conf) {
                    conf = new PropertiesUtil(name);
                    configMap.put(name, conf);
                }
            }
        }
        return conf;
    }

    public String get(String key) {
        return resourceBundle.getString(key);
    }

    public Integer getInt(String key) {
        return Integer.parseInt(resourceBundle.getString(key));
    }

}
