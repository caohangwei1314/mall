package cn.caohangwei.mall.common.util;


import cn.caohangwei.mall.common.base.BasePrefix;
import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

    private static String HOST = PropertiesUtil.getInstance("redis").get("redis.host");

    private static Integer PORT = PropertiesUtil.getInstance("redis").getInt("redis.port");

    private static Integer TIME_OUT = PropertiesUtil.getInstance("redis").getInt("redis.timeout");

    private static String PASSWORD = PropertiesUtil.getInstance("redis").get("redis.password");

    private static Integer POOL_MAX_TOTAL = PropertiesUtil.getInstance("redis").getInt("redis.poolMaxTotal");

    private static Integer POOL_MAX_IDLE = PropertiesUtil.getInstance("redis").getInt("redis.poolMaxIdle");

    private static Integer POOL_MAX_WAIT = PropertiesUtil.getInstance("redis").getInt("redis.poolMaxWait");

    private static JedisPool jedisPool = null;

    private static JedisPool init(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(POOL_MAX_TOTAL);
        jedisPoolConfig.setMaxIdle(POOL_MAX_IDLE);
        jedisPoolConfig.setMaxWaitMillis(POOL_MAX_WAIT * 1000);
        return new JedisPool(jedisPoolConfig,HOST,PORT,TIME_OUT * 1000,PASSWORD,0);
    }

    private static Jedis getJedis(){
        if(null == jedisPool){
            synchronized (RedisUtil.class){
                if(null == jedisPool){
                    jedisPool = init();
                }
            }
        }
        return jedisPool.getResource();
    }

    private static void close(Jedis jedis){
        if(null != jedis){
            jedis.close();
        }
    }

    /**
     * 获取单个对象
     */
    public static <T> T get(BasePrefix prefix, String name, Class<T> clazz){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            String key = prefix.getPrefix() + name;
            String value = jedis.get(key);
            return (T) JSONUtil.stringToBean(value,clazz);
        } finally {
            close(jedis);
        }
    }

    /**
     * 保存对象
     */
    public static <T> boolean set(BasePrefix prefix,String name,T value){
        if(value == null){
            return false;
        }
        Jedis jedis = null;
        try {
            jedis = getJedis();
            String str = JSONUtil.beanToString(value);
            String key = prefix.getPrefix() + name;
            int expireTime = prefix.getExpireTime();
            if(expireTime <= 0){
                jedis.set(key,str);
            }else {
                jedis.setex(key,expireTime,str);
            }
            return true;
        } finally {
            close(jedis);
        }
    }

    /**
     * 删除对象
     */
    public static <T> boolean delete(BasePrefix prefix,String name){
        if(name == null){
            return false;
        }
        Jedis jedis = null;
        try {
            jedis = getJedis();
            String key = prefix.getPrefix() + name;
            jedis.del(key);
            return true;
        } finally {
            close(jedis);
        }
    }

    /**
     * 判断是否存在
     */
    public static <T> boolean exists(BasePrefix prefix,String key){
        if(key == null){
            return false;
        }
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.exists(prefix.getPrefix() + key);
            return true;
        } finally {
            close(jedis);
        }
    }

    /**
     * 自增
     */
    public static <T> Long incr(BasePrefix prefix,String key){
        if(key == null){
            return 0L;
        }
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.incr(prefix.getPrefix() + key);
        } finally {
            close(jedis);
        }
    }

    /**
     * 自减
     */
    public static <T> Long decr(BasePrefix prefix,String key){
        if(key == null){
            return 0L;
        }
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.decr(prefix.getPrefix() + key);
        } finally {
            close(jedis);
        }
    }
}
