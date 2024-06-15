package controller;

public enum CodeMsgEnum  {

    UNKNOWN(-200, "unknown error"),
    FAILED(500, "failed"),
    SUCCESSFUL(200, "success"),
    NOT_ADMIN(1002, "没有管理员权限"),
    HEADER_NULL(1003, "Header is null"),
    PARAMETER_NULL(1004, "parameter is null"),
    PARAMETER_ERROR(1005, "parameter is error");
    private int code;
    private String msg;

    private CodeMsgEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
