package com.mashibing.dp.visitor.kafka;

/**
 * @ClassName SchemaException
 * @Description TODO
 * @Author fengxiaoxiao
 * @Date 2020/9/1 8:59
 * @Version 1.0
 */
public class SchemaException extends RuntimeException {

	private static final long serialVersionUID = 6163834776146724904L;

	SchemaException(String message) {
		super(message);
	}
}
