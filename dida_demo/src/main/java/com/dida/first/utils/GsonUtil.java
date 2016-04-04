package com.dida.first.utils;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class GsonUtil {
	public static <T> T json2Bean(String json,Class<T> clazz){
		Gson gson = new Gson();
		return gson.fromJson(json, clazz);
	}
	private <T> String  getJsonString(List<T> data) {
		ArrayList<LinkedHashMap<String, String>> linkedHashMaps = new ArrayList<>();
		for (int i = 0; i < data.size(); i++) {
			LinkedHashMap<String, String> stringStringLinkedHashMap = new LinkedHashMap<>();
			stringStringLinkedHashMap.put("productNo ", "21510215121221dfd0");
			stringStringLinkedHashMap.put("type", "1");
			stringStringLinkedHashMap.put("purchaseAttr", "0201202" + "$" + "XXL" + "$" + "尺寸");
			stringStringLinkedHashMap.put("count", "10");
			stringStringLinkedHashMap.put("isPrepay", "1");
			linkedHashMaps.add(stringStringLinkedHashMap);
		}
		Gson gson = new Gson();
		return gson.toJson(linkedHashMaps);

	}

}

