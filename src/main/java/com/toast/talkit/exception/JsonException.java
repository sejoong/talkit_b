package com.toast.talkit.exception;

public class JsonException extends RuntimeException{
	private static final long serialVersionUID = -7525840531532393157L;

	public JsonException(Throwable throwable){
		super("Json Parsing Error", throwable);
	}
	
	public JsonException(String message, Throwable e){
		super(message, e);
	}
}
