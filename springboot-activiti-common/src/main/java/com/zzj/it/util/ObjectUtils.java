package com.zzj.it.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils {

	public static byte[] serialize(Object obj) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;

		if (obj != null) {
			baos = new ByteArrayOutputStream();
			try {
				oos = new ObjectOutputStream(baos);
				oos.writeObject(obj);
				return baos.toByteArray();
			} catch (IOException e) {
			} finally {
				try {
					if (baos != null) {
						baos.close();
					}
					if (oos != null) {
						oos.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return null;
	}

	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		ObjectInputStream ois=null;
		try {
			if (bytes != null && bytes.length > 0) {
				bais = new ByteArrayInputStream(bytes);
				
				ois = new ObjectInputStream(bais);
				return ois.readObject();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (bais != null) {
					bais.close();
				}
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
}
