package controller;

public class ResultUtils {
    public ResultUtils() {
    }

    public static ResultBean success() {
        ResultBean resultBean = new ResultBean();
        resultBean.setMsg(CodeMsgEnum.SUCCESSFUL.getMsg());
        resultBean.setCode(CodeMsgEnum.SUCCESSFUL.getCode());
        return resultBean;
    }

    public static ResultBean error(CodeMsgEnum codeMsgEnum) {
        ResultBean resultBean = new ResultBean();
        if (codeMsgEnum == null) {
            codeMsgEnum = CodeMsgEnum.FAILED;
        }
        resultBean.setMsg(codeMsgEnum.getMsg());
        resultBean.setCode(codeMsgEnum.getCode());

        return resultBean;
    }


}
