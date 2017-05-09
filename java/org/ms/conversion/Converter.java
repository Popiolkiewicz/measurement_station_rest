package org.ms.conversion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created on 05.04.2017 21:48:58
 * 
 * @author Hubert Popio³kiewicz
 */
public class Converter {

	private Gson gson;

	public Converter() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gson = gsonBuilder.create();
	}

	public <TYPE extends Object> TYPE toPOJO(String json, Class<TYPE> clazz) {
		return (TYPE) gson.fromJson(json, clazz);
	}

	public String toJSON(Object object) {
		return gson.toJson(object);
	}
}
