package cn.caohangwei.mall.common.base;

public abstract class BasePrefix {

    private String prefix;

    private int expireTime;

    public BasePrefix(String prefix){
        this.prefix = prefix;
    }

    public BasePrefix(String prefix,int expireTime){
        this.prefix = prefix;
        this.expireTime = expireTime;
    }

    public String getPrefix(){
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }

    public int getExpireTime() {
        return expireTime;
    }
}
