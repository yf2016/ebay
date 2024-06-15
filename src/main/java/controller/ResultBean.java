package controller;

public class ResultBean<T> {

    private boolean success = true;//
    private int code = 200;
    private String msg = "执行成功";
    private T data;

    public ResultBean() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

//    private ResultBean(CodeMsgEnum enumMsg) {
//        this.success = true;
//        this.msg = enumMsg.getMsg();
//        int code = enumMsg.getCode();
//    }
}
