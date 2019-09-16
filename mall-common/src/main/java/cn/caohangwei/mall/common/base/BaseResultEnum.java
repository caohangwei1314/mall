package cn.caohangwei.mall.common.base;

public enum  BaseResultEnum {

    FAILED(0,"failed"),
    SUCCESS(1,"success"),
    PASSWORD_EMPTY(500001,"登陆密码不能为空"),
    PHONE_EMPTY(500002,"手机号码不能为空"),
    PHONE_ERROR(500003,"手机格式错误"),
    PHONE_NOT_EXISTS(500004,"用户不存在"),
    PASSWORD_ERROR(500005,"用户不存在"),
    SPIKE_ERROR(500006,"秒杀失败"),
    PURCHASED_ERROR(500007,"您已购买过"),
    GOODS_ERROR(50008,"商品不存在"),
    SESSION_ERROR(50009,"请登录"),
    ORDER_NOT_EXISTS(50010,"订单不存在"),
    PATH_NOT_EXISTS(50011,"路径不存在"),
    VERIFY_ERROR(50012,"验证码错误");

    BaseResultEnum(int coed,String msg){
        this.code = coed;
        this.msg = msg;
    }

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
