package cn.caohangwei.mall.common.base;

import java.io.Serializable;

/**
 * Partition returns result class.
 */
public class BaseResult implements Serializable {

    private Integer code;

    private String msg;

    private Object data;

    private static final long serialVersionUID = 1L;

    public BaseResult(int code,String msg,Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResult(BaseResultEnum baseEnum,Object data){
        this.code = baseEnum.getCode();
        this.msg = baseEnum.getMsg();
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", code=").append(code);
        sb.append(", msg=").append(msg);
        sb.append(", data=").append(data);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BaseResult other = (BaseResult) that;
        return (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
                && (this.getMsg() == null ? other.getMsg() == null : this.getMsg().equals(other.getMsg()))
                && (this.getData() == null ? other.getData() == null : this.getData().equals(other.getData()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getMsg() == null) ? 0 : getMsg().hashCode());
        result = prime * result + ((getData() == null) ? 0 : getData().hashCode());
        return result;
    }
}
