package base;

import java.io.Serializable;

/**
 * 自定义的Json模型
 */
public class Json implements Serializable {

    private static final long serialVersionUID = -2942787198846252437L;

    private boolean success = false;// 是否成功
    private String msg = "";// 提示信息
    private Object obj = null;// 其他信息

    public Json() {
    }//无参构造

    public Json(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
