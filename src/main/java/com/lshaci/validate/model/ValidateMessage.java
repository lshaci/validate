package com.lshaci.validate.model;

/**
 * 验证未通过提示消息
 * 
 * @author lshaci
 */
public class ValidateMessage {
	
	private String verify;		// 验证器名称
	private String field;		// 验证的字段
	private String message;		// 验证对应字段的提示消息
	
	public ValidateMessage() {
		super();
	}

	/**
	 * 构建一个验证信息
	 * 
	 * @param verify	验证器名称
	 * @param field		验证的字段名
	 * @param message	消息提示
	 */
	public ValidateMessage(String verify, String field, String message) {
		super();
		this.verify = verify;
		this.field = field;
		this.message = message;
	}

	public String getVerify() {
		return verify;
	}
	public void setVerify(String verify) {
		this.verify = verify;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ValidateMessage [verify=" + verify + ", field=" + field + ", message=" + message + "]";
	}
	
	
}
