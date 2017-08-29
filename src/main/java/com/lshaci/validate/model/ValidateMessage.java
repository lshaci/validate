package com.lshaci.validate.model;

/**
 * 验证未通过提示消息
 * 
 * @author lshaci
 */
public class ValidateMessage {
	
	private String fieldName;	// 验证的字段
	private String message;		// 验证对应字段的提示消息
	
	public ValidateMessage() {
		super();
	}
	
	public ValidateMessage(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ValidateMessage [fieldName=" + fieldName + ", message=" + message + "]";
	}
	
}
