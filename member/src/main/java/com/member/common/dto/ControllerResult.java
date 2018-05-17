package com.member.common.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 统一的json返回格式
 * @author wzhz
 * @param <T>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ControllerResult<T> {
	private boolean success;
	private T data;
	private String msg;
	private String code;// 错误码

	public ControllerResult(boolean success, T data, String msg) {
		this.success = success;
		this.data = data;
		this.msg = msg;
	}

	public ControllerResult(boolean success, T data, String msg,String code) {
		this.success = success;
		this.data = data;
		this.msg = msg;
		this.code = code;
	}

	/**
	 * 正确信息
	 * @param data	返回参数
	 */
	public ControllerResult(T data) {
		this.success = true;
		this.data = data;
	}

	public ControllerResult(boolean success, String msg) {
		this.msg = msg;
		this.success = success;
	}

	public ControllerResult() {
	}
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "返回信息： [success=" + success + ", data=" + data + ", msg=" + msg + ", code=" + code + "]";
	}

}
