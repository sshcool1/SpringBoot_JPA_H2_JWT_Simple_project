package com.hellcow.testBook.utils;

import com.hellcow.testBook.entity.UserEntity;
import com.sun.tools.javac.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ConvertUtil {

	public static Map converObjectToMap(Object obj) {
		try {
			Field[] fields = obj.getClass().getDeclaredFields();
			Map resultMap = new HashMap();
			for (int i = 0; i <= fields.length - 1; i++) {
				fields[i].setAccessible(true);
				resultMap.put(fields[i].getName(), fields[i].get(obj));
			}
			return resultMap;
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object convertMapToObject(Map<String,Object> map, Object obj){
		String keyAttribute = null;
		String setMethodString = "set";
		String methodString = null;
		Iterator itr = map.keySet().iterator();

		while(itr.hasNext()){
			keyAttribute = (String) itr.next();
			methodString = setMethodString+keyAttribute.substring(0,1).toUpperCase()+keyAttribute.substring(1);
			Method[] methods = obj.getClass().getDeclaredMethods();
			for(int i=0;i<methods.length;i++){
				if(methodString.equals(methods[i].getName())){
					try{
						methods[i].invoke(obj, map.get(keyAttribute));
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}
		return obj;
	}

	public static Object convertEntityToDTO(UserEntity userEntity, Object dto) {
		try {
			Field[] fields = dto.getClass().getDeclaredFields();
			for (int i = 0; i <= fields.length - 1; i++) {
				fields[i].setAccessible(true);
				String methodString = fields[i].getName();
				if(StringUtils.indexOfIgnoreCase(methodString, "set") == -1){
					continue;
				}

				Method[] methods = userEntity.getClass().getDeclaredMethods();
				String getMethodStr = methodString.replace("set", "get");
				for(int j=0;j<methods.length;j++){
					if(getMethodStr.equals(methods[j].getName())){
						try{
							methods[j].invoke(methodString, fields[i].get(userEntity));
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
			}
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
}
