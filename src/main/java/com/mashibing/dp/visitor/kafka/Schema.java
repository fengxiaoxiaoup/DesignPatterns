package com.mashibing.dp.visitor.kafka;

import java.nio.ByteBuffer;

/**
 * @ClassName Schema
 * @Description TODO
 * @Author fengxiaoxiao
 * @Date 2020/9/1 13:14
 * @Version 1.0
 */
public class Schema extends Type{
	@Override
	public void write(ByteBuffer buffer, Object o) {

	}

	@Override
	public Object read(ByteBuffer buffer) {
		return null;
	}

	@Override
	public Object validate(Object o) {
		return null;
	}

	@Override
	public int sizeOf(Object o) {
		return 0;
	}
}
