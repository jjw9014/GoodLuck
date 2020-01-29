package com.help.api;

import java.io.Serializable;

public class ResultDTO<T> implements  Serializable{

	/**
	 * Created by 000965 on 2018/01/20.
	 */
	private static final long serialVersionUID = -5956087567194696411L;
	
	private String code;//返回码
	private String message;//返回信息
	private T data;//具体相关的dto
	private Integer count;//针对部分批量查询接口提供符合条件的所有数量，只在访问第1页时提供数量
	private boolean success =true;//默认真

	public ResultDTO() {}

	public ResultDTO(String code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public ResultDTO(String code, String message, T data, boolean success) {
		this.code = code;
		this.message = message;
		this.data = data;
		this.success = success;
	}

	public ResultDTO(String code, String message, T data, boolean success, Integer count) {
		this.code = code;
		this.message = message;
		this.data = data;
		this.success = success;
		this.count = count;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
