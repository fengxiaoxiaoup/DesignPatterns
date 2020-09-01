package com.mashibing.dp.visitor.kafka;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName Type
 * @Description TODO
 * @Author fengxiaoxiao
 * @Date 2020/9/1 8:40
 * @Version 1.0
 */
public abstract class Type {
	public abstract void write(ByteBuffer buffer , Object o);
	public abstract Object read(ByteBuffer buffer);
	public abstract Object validate(Object o);
	public abstract int sizeOf(Object o);
	public boolean isNullable() {
		return false;
	}

	public static abstract class DocumentedType extends Type {
		public abstract String typeName();
		public abstract String documentation();
	}

	public static final DocumentedType BOOLEAN = new DocumentedType() {


		@Override
		public String typeName() {
			return "BOOLEAN";
		}

		@Override
		public String documentation() {
			return "Represents a boolean value in a byte. " +
					"Values 0 and 1 are used to represent false and true respectively. " +
					"When reading a boolean value, any non-zero value is considered true.";
		}

		@Override
		public void write(ByteBuffer buffer, Object o) {
			if ((Boolean)o) {
				buffer.put((byte)1);
			} else {
				buffer.put((byte)0);
			}
		}

		@Override
		public Object read(ByteBuffer buffer) {
			byte value = buffer.get();
			return value != 0;
		}

		@Override
		public Object validate(Object o) {
			if (o instanceof Boolean) {
				return (Boolean) o;
			}
			return new SchemaException(o + "not a boolean type");
		}

		@Override
		public int sizeOf(Object o) {
			return 1;
		}
	};

	public static final DocumentedType INT8  = new DocumentedType() {

		@Override
		public String typeName() {
			return null;
		}

		@Override
		public String documentation() {
			return "Represents an integer between -2<sup>7</sup> and 2<sup>7</sup>-1 inclusive.";
		}

		@Override
		public void write(ByteBuffer buffer, Object o) {
			buffer.put((Byte) o);
		}

		@Override
		public Object read(ByteBuffer buffer) {
			return buffer.get();
		}

		@Override
		public Object validate(Object item) {
			if (item instanceof  Byte) {
				return (Byte)item;
			} else {
				throw new SchemaException(item + "is not a byte");
			}
 		}

		@Override
		public int sizeOf(Object o) {
			return 1;
		}
	};

	public static final DocumentedType INT16 = new DocumentedType() {
		@Override
		public String typeName() {
			return "INT16";
		}

		@Override
		public String documentation() {
			return "Represents an integer between -2<sup>15</sup> and 2<sup>15</sup>-1 inclusive. " +
					"The values are encoded using two bytes in network byte order (big-endian).";
		}

		@Override
		public void write(ByteBuffer buffer, Object o) {
			buffer.putShort((Short)o);
		}

		@Override
		public Object read(ByteBuffer buffer) {
			return buffer.getShort();
		}

		@Override
		public Object validate(Object o) {
			if (o instanceof Short) {
				return (Short) o;
			} else {
				throw new SchemaException(o + "not a short.");
			}
		}

		@Override
		public int sizeOf(Object o) {
			return 2;
		}
	};

	public static final DocumentedType INT32 = new DocumentedType() {
		@Override
		public String typeName() {
			return "INT32";
		}

		@Override
		public String documentation() {
			return "Represents an integer between -2<sup>31</sup> and 2<sup>31</sup>-1 inclusive. " +
					"The values are encoded using four bytes in network byte order (big-endian).";
		}

		@Override
		public void write(ByteBuffer buffer, Object o) {
			buffer.putInt((Integer) o);
		}

		@Override
		public Object read(ByteBuffer buffer) {
			return buffer.getInt();
		}

		@Override
		public Object validate(Object o) {
			if (o instanceof Integer) {
				return (Integer) o;
			} else {
				throw new SchemaException(o + "not a int .");
			}
		}

		@Override
		public int sizeOf(Object o) {
			return 4;
		}
	};

	public static final DocumentedType INSIGNED_INT32 = new DocumentedType() {
		@Override
		public String typeName() {
			return "INSIGNED_INT32";
		}

		@Override
		public String documentation() {
			return "Represents an integer between 0 and 2<sup>32</sup>-1 inclusive. " +
					"The values are encoded using four bytes in network byte order (big-endian).";
		}

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
	};

	public static final DocumentedType INT64 = new DocumentedType() {
		@Override
		public String typeName() {
			return "INT64";
		}

		@Override
		public String documentation() {
			return "Represents an integer between -2<sup>63</sup> and 2<sup>63</sup>-1 inclusive. " +
					"The values are encoded using eight bytes in network byte order (big-endian).";
		}

		@Override
		public void write(ByteBuffer buffer, Object o) {
			buffer.putLong((Long)o);
		}

		@Override
		public Object read(ByteBuffer buffer) {
			return buffer.getLong();
		}

		@Override
		public Object validate(Object o) {
			if (o instanceof Long) {
				return (Long)o;
			} else {
				throw new SchemaException(o + "not a long value");
			}
		}

		@Override
		public int sizeOf(Object o) {
			return 8;
		}
	};

	public static final DocumentedType STRING = new DocumentedType() {
		@Override
		public String typeName() {
			return "STRING";
		}

		@Override
		public String documentation() {
			return null;
		}

		@Override
		public void write(ByteBuffer buffer, Object o) {
			byte[] bytes = ((String)o).getBytes(StandardCharsets.UTF_8);
			if (bytes.length > Short.MAX_VALUE) {
				throw new SchemaException("too long");
			}
			buffer.putShort((short)bytes.length);
			buffer.put(bytes);
		}

		@Override
		public Object read(ByteBuffer buffer) {
			short length = buffer.getShort();
			if (length < 0) {
				throw new SchemaException("cannt be negative");
			}

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
	};

}
