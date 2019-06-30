package com.iinaq.springboot.exception;

public class CustomException extends RuntimeException{

    private static final long serialVersionUID = -6522577322897454378L;

    private int code;

    public CustomException(){
        super();
    }

    public CustomException(int code, String msg){
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
