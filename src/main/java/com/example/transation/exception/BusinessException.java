package com.example.transation.exception;


import com.example.transation.enums.CodeEnum;

/**
 * @Author ：xzh
 * @Description:
 * @Date: 11:25 2018/5/13
 * @Modified by: xzh 15:25 2018/6/13
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Integer errorCode;

    private CodeEnum exceptionCodeEnum;

    public BusinessException() {
        super();
    }

    public BusinessException(Throwable e) {
        super(e);
    }

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(String msg, Throwable e) {
        super(msg, e);
    }

    public BusinessException(String msg, Throwable e, Integer errorCode) {
        super(msg, e);
        this.errorCode = errorCode;
    }

    public BusinessException(CodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
        this.exceptionCodeEnum = exceptionCodeEnum;
        this.errorCode = exceptionCodeEnum.getCode();
    }

    public BusinessException(int code, String msg) {
        super(msg);
        this.errorCode = code;
    }

    public BusinessException(Exception e){
        super(CodeEnum.SYSTEM_ERROR.getMessage());
        this.exceptionCodeEnum = CodeEnum.SYSTEM_ERROR;
        this.errorCode = CodeEnum.SYSTEM_ERROR.getCode();
    }

    public BusinessException(String msg, Integer errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public CodeEnum getExceptionCodeEnum() {
        return exceptionCodeEnum;
    }
}
