package com.member.common.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author wzhz
 * @param <T>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class RequestDto<T> {

	private String code;
	private String message;
	private T data;
	private String time;
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResponseDto [code=" + code + ", message=" + message + ", data=" + data + ", time=" + time + "]";
	}
	public RequestDto(String code, String message, T data, String time) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
		this.time = time;
	}
	public RequestDto() {
		super();
	}

}
