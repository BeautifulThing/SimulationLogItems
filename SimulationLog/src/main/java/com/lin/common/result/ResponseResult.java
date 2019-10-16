package com.lin.common.result;

import lombok.Data;

@Data
public class ResponseResult<T> {
    private int code;
    private String msg;
    private T data;
    /**
     * 成功时候的调用
     */
    public static <T> ResponseResult<T> success() {
        return new ResponseResult<T>(200, "成功");
    }

    /**
     * 成功时候的调用
     */
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<T>(200, "成功", data);
    }

    /**
     * 失败时候的调用
     */
    public static <T> ResponseResult<T> error(CodeMsg codeMsg) {
        return new ResponseResult<T>(codeMsg);
    }
    private ResponseResult(T data) {
        this.data = data;
    }

    private ResponseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ResponseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private ResponseResult(CodeMsg codeMsg) {
        if (codeMsg != null) {
            this.code = codeMsg.getCode();
            this.msg = codeMsg.getMsg();
        }
    }
}
