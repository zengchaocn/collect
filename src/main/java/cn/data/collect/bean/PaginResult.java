package cn.data.collect.bean;

import com.github.pagehelper.PageInfo;

import java.util.List;

public class PaginResult<T> extends PageInfo<T> {
    private int code;
    private String msg;

    public PaginResult() {
        super();
    }

    public PaginResult(List<T> list) {
        super(list);
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
}
