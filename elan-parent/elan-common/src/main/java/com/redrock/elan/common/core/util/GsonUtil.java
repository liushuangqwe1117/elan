package com.redrock.elan.common.core.util;

import java.lang.reflect.Type;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class GsonUtil {
	public static final Type JsonObjectType = new TypeToken<JsonObject>(){}.getType();
	public static final Type JsonObjectArray = new TypeToken<JsonArray>(){}.getType();

	public static final Type StringArrayType = new TypeToken<String[]>(){}.getType();

	public static final Type StringHashMapType = new TypeToken<HashMap<String, String>>(){}.getType();

	public static final Gson gson = new GsonBuilder()  
	        .excludeFieldsWithoutExposeAnnotation() //不导出实体中没有用@Expose注解的属性  
	        .serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

}
