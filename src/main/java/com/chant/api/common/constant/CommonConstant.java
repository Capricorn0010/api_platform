package com.chant.api.common.constant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class CommonConstant {

	/**
	 * 包名配置
	 */
	public static final String BASE_PACKAGE = "com.chant.api";

	/**
	 * JSR 303 javax 包名
	 */
	public static final String JAVAX_VALIDATION = "javax.validation";

	/**
	 * spring 的验证包名
	 */
	public static final String SPRING_VALIDATION = "org.springframework.validation";

	/**
	 * spring web 下 参数绑定的包名
	 */
	public static final String SPRING_WEB_BIND = "org.springframework.web.bind";
	public static final String SPRING_HTTP_CONVERTER = "org.springframework.http.converter";

	/**
	 * 检索 和 排序 字段非法异常
	 */
	public static final String SEARCH_NOT_ALLOW = "org.springframework.dao.InvalidDataAccessApiUsageException";
	public static final String SORT_NOT_ALLOW = "org.springframework.data.mapping.PropertyReferenceException";

	/**
	 *  DAO下错误
	 */
	public static final String DAO_OPERATE_EXCEPTION = "org.springframework.dao.DataIntegrityViolationException";

	/**
	 * 异常返回字段名
	 */
	public static final String STATUS_CODE = "status";
	public static final String ERROR_CODE = "error";
	public static final String MESSAGE = "message";
	public static final String TIMESTAMP = "timestamp";


	/**
	 *  异常类型
	 */
	public static final String SYSTEM_EXCEPTION = "系统异常";
	public static final String CUSTOM_EXCEPTION = "业务异常";

	/**
	 * token
	 */
	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String TOKEN_REFRESH = "X-Refresh-Token";
	public static final String TOKEN_SECRET = "ThisisthelandraywwwSYSTEMsecret20190828";

	/**
	 * 换行符
	 */
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	/**
	 * 请求头
	 */
	public static final String X_REAL_IP = "X-Real-IP";
	public static final String X_FORWARDED_FOR = "X-Forwarded-For";

	/**
	 * 日志输出格式
	 */
	public static final String LOG_REQUEST_FORMAT = LINE_SEPARATOR + "@->> 接收请求日志:{}" ;
	public static final String LOG_RESPONSE_FORMAT = LINE_SEPARATOR+"<<-@ 响应请求日志:{}";
	public static final String LOG_EXCEPTION_BEFORE = LINE_SEPARATOR + "API日志拦截器before 出现异常.";
	public static final String LOG_EXCEPTION_AFTER_RETURNING = LINE_SEPARATOR + "API日志拦截器after returning 出现异常.";
	public static final String LOG_THROWING_AFTER_THROWING = LINE_SEPARATOR + "API日志拦截器after throwing 出现异常.";
	public static final String LOG_JWT_AUTHENTICATION_SUCCESS = LINE_SEPARATOR + "JWT鉴权成功 :";


}
