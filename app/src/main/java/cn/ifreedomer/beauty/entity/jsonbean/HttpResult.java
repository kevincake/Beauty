package cn.ifreedomer.beauty.entity.jsonbean;

/**
 * Created by eavawu on 4/28/16.
 */
public class HttpResult<T> {
    private int resultCode;
    private String msg;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
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

    public T data;

}
