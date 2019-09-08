package cn.caohangwei.mall.common.base;

public enum  BaseResultEnum {

    FAILED(0,"failed"),
    SUCCESS(1,"success"),
    PASSWORD_EMPTY(500001,"登陆密码不能为空"),
    PHONE_EMPTY(500002,"手机号码不能为空"),
    PHONE_ERROR(500003,"手机格式错误"),
    PHONE_NOT_EXISTS(500004,"用户不存在"),
    PASSWORD_ERROR(500005,"用户不存在");

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
