package com.zzj.it.common.shiro.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 序列化反序列化工具
 * @author zhouzj
 * @version 1.3.3
 */
public class ByteSouceUtils {

	private static final Logger logger = LoggerFactory.getLogger(ByteSouceUtils.class);

	public static byte[] serialize(Object value) {
		if (value == null) {
			throw new NullPointerException("无法序列化");
		}
		byte[] rv = null;
		ByteArrayOutputStream bs = null;
		ObjectOutputStream os = null;
		try {
			bs=new ByteArrayOutputStream();
			os=new ObjectOutputStream(bs);
			os.writeObject(value);
			os.close();
			bs.close();
			rv=bs.toByteArray();
		} catch (Exception e) {
			logger.error("序列化异常", e);
		} finally {
			colse(bs);
			colse(os);
		}
		return rv;
	}
	
	public static Object deserialize(byte[] in) {
		return deserialize(in,Object.class);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T deserialize(byte[] in,Class<Object> class1){
		Object rv=null;
		ByteArrayInputStream bis=null;
		ObjectInputStream is=null;
		try {
			if(in !=null&&in.length>0) {
				bis=new ByteArrayInputStream(in);
				is = new ObjectInputStream(bis);
				rv=is.readObject();
			}
		}catch(Exception e) {
			logger.error("反序列化异常", e);
		}finally {
			colse(is);
			colse(bis);
		}
		return (T) rv;
	}
	
	public static void colse(Closeable closeable) {
		if(closeable!=null) {
			try {
				closeable.close();
			} catch (IOException e) {
				logger.error("io流关闭异常", e);
			}
		}
	}
}
