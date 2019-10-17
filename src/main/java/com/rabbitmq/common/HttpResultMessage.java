package com.rabbitmq.common;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * 接口统一返回格式实体类
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class HttpResultMessage {

	public HttpResultMessage() {
	}

	@SuppressWarnings("unchecked")
	public HttpResultMessage(Object requestSign) {
		this.requestSignMap = ((Map<String, Object>) requestSign);
	}

	public HttpResultMessage(int httpCode, String httpMessage) {
		this.httpCode = httpCode;
		this.httpMessage = httpMessage;
	}

	/**
	 * http请求状态码
	 */
	private Integer httpCode = 200;
	/**
	 * 错误代码
	 */
	private String errorCode = null;
	/**
	 * http请求返回的消息，可以用于业务自定义信息
	 **/
	private String httpMessage = "success";

	/**
	 * 系统架构捕获到的异常信息，测试机时期可以考虑在前端App显示出来，
	 * 考虑到该信息只能帮助修改程序错误，对于正式用户来讲不友好，正式上线后则关闭该信息显示，改成其他友好信息，如“系统繁忙，请稍后。”。
	 **/
	private String sysExceptionMessage;
	/**
	 * http请求返回的业务数据
	 **/
	private Object businessData;

	/**
	 * 新创建的实体ID值
	 **/
	private Long entityId;
	/**
	 * 请求标识唯一码值
	 **/
	private Map<String, Object> requestSignMap;

	/**
	 * 消息代码
	 */
	private String msgCode;

	public static String failed() {
		return failed("系统繁忙，请稍后。");
	}

	public static String failed(String msg) {
		return failed(0, msg);
	}

	public static String failed(int code, String msg) {
		return JSON.toJSONString(new HttpResultMessage(code, msg));
	}
}
