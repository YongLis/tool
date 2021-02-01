package com.sh.lang.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import scala.reflect.internal.Trees.New;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonParseUtil {

	public static Map<String, String> parseJson(String jsonString) {
		Map<String, String> map = new HashMap<String, String>();
		JSONObject jsonObject = new JSONObject().fromString(jsonString);
		JSONArray jsonArray = jsonObject.names();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jo = jsonArray.getJSONObject(i);
			System.out.println(jo.getString("goodsld"));
			System.out.println(jo.getString("goodsq"));
		}
		return map;
	}

	public static Map<String, Object> IteratorHash(JSONObject jsonObject) {
		Iterator<?> it = jsonObject.keys();
		HashMap<String, Object> RMap = new HashMap<String, Object>();

		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			if (jsonObject.get(key).getClass() == net.sf.json.JSONArray.class) {// 判是否为列表
				if (jsonObject.getJSONArray(key).isEmpty()) {// 判列表是否为空
					RMap.put(key, null);
				} else {

					List<Map<String, Object>> MapListObj = new ArrayList<Map<String, Object>>();
					for (Object JsonArray : jsonObject.getJSONArray(key)) {
						HashMap<String, Object> TempMap = new HashMap<String, Object>();
						if (JsonArray.getClass() == String.class) {
							TempMap.put(key, JsonArray);
						} else {
							TempMap.putAll(IteratorHash(net.sf.json.JSONObject
									.fromObject(JsonArray)));
						}
						MapListObj.add(TempMap);
					}
					RMap.put(key, (Object) MapListObj);
				}
			} else if (jsonObject.get(key).getClass() == net.sf.json.JSONObject.class) {

				RMap.put(key, jsonObject.getJSONObject(key));

			} else if (jsonObject.get(key).getClass() == String.class
					|| jsonObject.get(key).getClass() == Integer.class
					|| jsonObject.get(key).getClass() == Long.class) {

				RMap.put(key, jsonObject.get(key));

			}
		}
		return RMap;
	}
	
	
	/**
	 *
	 */
	
	public static Map<String, String> IteratorJSON(JSONObject jsonObject, String key, Map<String, String> map) {
		Iterator<?> it = jsonObject.keys();
		if(map == null){
			map = new HashMap<String, String>();
		}
		

		while (it.hasNext()) {
			String currentKey = String.valueOf(it.next());
			if(jsonObject.get(currentKey) instanceof JSONObject){
				map.put(key+"."+currentKey, jsonObject.toString());
				IteratorJSON((JSONObject) jsonObject.get(currentKey), key+"."+currentKey, map);
			}
			else if(jsonObject.get(currentKey) instanceof String){
				put2Map(map,key+"."+currentKey, jsonObject.getString(currentKey));
			}
			else if(jsonObject.get(currentKey) instanceof Number ){
				put2Map(map,key+"."+currentKey, jsonObject.get(currentKey).toString());
			}
			else if(jsonObject.get(currentKey) instanceof Boolean ){
				put2Map(map,key+"."+currentKey, jsonObject.getBoolean(currentKey)+"");
			}
			else if(jsonObject.get(currentKey) instanceof JSONArray ){
				
				JSONArray jsonArray = jsonObject.getJSONArray(currentKey);
//				map.put(key+"."+currentKey, jsonArray.toString());
				
				for(int i=0;i<jsonArray.size();i++) {
					IteratorJSON(jsonArray.getJSONObject(i), key+"."+currentKey, map);	
		        }
				
			}
			
		}
		return map;
	}
	
	
	public static Map<String, String> IteratorJSON2(JSONObject jsonObject, String key, Map<String, String> map) {
		Iterator<?> it = jsonObject.keys();
		if(map == null){
			map = new HashMap<String, String>();
		}
		

		
		return map;
	}
	
	
	
	private static void put2Map(Map<String, String> map, String key, String value){
		if(map == null){
			return;
		}
		
		if(map.containsKey(key)){
			String oldValue = map.get(key);
		    if(StringUtils.isNotEmpty(oldValue)){
		    	map.put(key, oldValue+"|"+value);
		    }
		    else{
		    	map.put(key, value);
		    }
		}
		else{
			map.put(key, value);
		}
		
	}

	public static void main(String[] args) {
		String json = "{\"device\":{\"fingerPrintId\":null},\"trade\":{\"code\":\"4722\",\"item\":{\"customerResource\":\"\",\"cust\":{\"registrationIP\":null,\"registrationEmail\":null,\"registrationPhone\":null,\"registrationTime\":null,\"lastShoppingTime\":null},\"orderIP\":\"106.192.48.31\",\"payTime\":\"20191213125250\",\"airline\":[{\"flightNumber\":\"262\",\"takeoffTime\":\"20200301034000\",\"arrivalTime\":\"20200301060000\",\"takeoffCity\":\"MNL\",\"landingCity\":\"TPE\",\"insurance\":\"false\",\"aircraftCabinType\":\"C\",\"tripType\":\"02\",\"companyID\":\"BR\"},{\"flightNumber\":\"18\",\"takeoffTime\":\"20200301180000\",\"arrivalTime\":\"20200301122500\",\"takeoffCity\":\"TPE\",\"landingCity\":\"YVR\",\"insurance\":\"false\",\"aircraftCabinType\":\"C\",\"tripType\":\"02\",\"companyID\":\"AC\"},{\"flightNumber\":\"17\",\"takeoffTime\":\"20200813110000\",\"arrivalTime\":\"20200814141500\",\"takeoffCity\":\"YVR\",\"landingCity\":\"TPE\",\"insurance\":\"false\",\"aircraftCabinType\":\"C\",\"tripType\":\"02\",\"companyID\":\"AC\"},{\"flightNumber\":\"895\",\"takeoffTime\":\"20200814210000\",\"arrivalTime\":\"20200814232500\",\"takeoffCity\":\"TPE\",\"landingCity\":\"MNL\",\"insurance\":\"false\",\"aircraftCabinType\":\"C\",\"tripType\":\"02\",\"companyID\":\"PR\"}],\"ifRoundtripFlight\":\"true\",\"ifConnectingFlight\":\"false\",\"connectingCity1\":\"\",\"connectingCity2\":\"\",\"connectingCity3\":\"\",\"adultNumber\":\"1\",\"childrenNumber\":\"0\",\"babyNumber\":\"0\",\"passenger\":[{\"orderPhoner\":\"9779023016\",\"orderEmail\":\"kulwinder_aytan@yahoo.com\",\"firstName\":\"SONIARANI\",\"lastName\":\"SONIARANI\",\"certificatesType\":\"PP\",\"certificatesNum\":\"S7724285\",\"country\":\"IN\",\"phone\":\"9779023016\",\"birthDate\":\"19740909\"}]}},\"cust\":{\"registerUserId\":\"a06463442abe432cbfe78cf476db126a\",\"ip\":\"106.192.48.31\",\"email\":\"kulwinder_aytan@yahoo.com\",\"phoneNum\":\"9779023016\",\"registrationTime\":null,\"level\":null,\"lastShoppingTime\":null},\"goods\":[{\"sku\":\"air ticket|MNL-YVR/YVR-MNL\",\"itemPrice\":\"4053.0\",\"quantity\":\"1\",\"isGift\":\"no\",\"isVirtual\":\"no\"}]}";

		Map<String, String> map = IteratorJSON(JSONObject.fromString(json),"riskInfo", null);
		System.out.println(new GsonBuilder().disableHtmlEscaping().create().toJson(map));

	}

}
