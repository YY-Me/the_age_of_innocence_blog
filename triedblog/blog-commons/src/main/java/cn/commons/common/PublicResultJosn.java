package cn.commons.common;

/**
 * 公共返回数据json
 * 
 * @author 偶尔有点困
 * @date 2018年5月5日
 */
public class PublicResultJosn {

    private Integer code;

    private String message;

    private Object data;

    public PublicResultJosn() {
        super();
    }

    public PublicResultJosn(Integer code, String message, Object data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
