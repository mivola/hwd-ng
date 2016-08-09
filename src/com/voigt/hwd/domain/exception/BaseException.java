package com.voigt.hwd.domain.exception;

import com.google.gwt.user.client.rpc.IsSerializable;

public class BaseException extends RuntimeException implements IsSerializable {

    private static final long serialVersionUID = -7027582944557339829L;

    private String message;

    public BaseException() {
	// needed for gilead
    }

    public BaseException(String message) {
	this.message = message;
    }

    public BaseException(String msg, Exception e) {
	// TODO Auto-generated constructor stub
    }

    public void setMessage(String message) {
	this.message = message;
    }

    @Override
    public String getMessage() {
	return message;
    }
}
