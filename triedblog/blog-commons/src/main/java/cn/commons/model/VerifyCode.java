package cn.commons.model;

import java.io.Serializable;

public class VerifyCode implements Serializable {

    private static final long serialVersionUID = -3817152990885945092L;

    private String code;

    private String codeStr;

    public VerifyCode() {
        super();
    }

    public VerifyCode(String code, String codeStr) {
        super();
        this.code = code;
        this.codeStr = codeStr;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeStr() {
        return codeStr;
    }

    public void setCodeStr(String codeStr) {
        this.codeStr = codeStr;
    }

}
