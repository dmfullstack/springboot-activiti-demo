package com.zzj.it.common.shiro.util;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.util.ByteSource;

/**
 * 反序列化处理
 * @author zhouzj
 * @version 1.3.3
 */
public class MySimpleByteSource implements ByteSource, Serializable {

	private static final long serialVersionUID = 6649013765285288370L;

	private byte[] bytes;

	private String cachedHex;

	private String cachedBase64;

	public MySimpleByteSource() {

	}

	public MySimpleByteSource(byte[] bytes) {
		this.bytes = bytes;
	}

	public MySimpleByteSource(char[] ch) {
		this.bytes = CodecSupport.toBytes(ch);
	}

	public MySimpleByteSource(String string) {
		this.bytes = CodecSupport.toBytes(string);
	}

	public MySimpleByteSource(ByteSource byteSource) {
		this.bytes = byteSource.getBytes();
	}

	public MySimpleByteSource(File file) {
		this.bytes = (new MySimpleByteSource.BytesHelper()).getBytes(file);
	}

	public MySimpleByteSource(InputStream inputStream) {
		this.bytes = (new MySimpleByteSource.BytesHelper()).getBytes(inputStream);
	}

	public static boolean isCompatible(Object o) {
		return o instanceof byte[] || o instanceof char[] || o instanceof String || o instanceof ByteSource
				|| o instanceof File || o instanceof InputStream;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	@Override
	public byte[] getBytes() {

		return this.bytes;
	}

	@Override
	public String toHex() {
		if (this.cachedHex == null) {
			this.cachedHex = Hex.encodeToString(this.getBytes());
		}
		return this.cachedHex;
	}

	@Override
	public String toBase64() {
		if (this.cachedBase64 == null) {
			this.cachedBase64 = Base64.encodeToString(this.getBytes());
		}
		return this.cachedBase64;
	}

	@Override
	public int hashCode() {
		return this.bytes != null && this.bytes.length != 0 ? Arrays.hashCode(this.bytes) : 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (obj instanceof ByteSource) {
			ByteSource bs = (ByteSource) obj;
			return Arrays.equals(this.bytes, bs.getBytes());
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		return this.bytes == null || this.bytes.length == 0;
	}

	private static final class BytesHelper extends CodecSupport {
		private BytesHelper() {

		}

		public byte[] getBytes(File file) {
			return this.getBytes(file);
		}

		public byte[] getBytes(InputStream stream) {
			return this.toBytes(stream);
		}

	}

}
