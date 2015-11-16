package com.test.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;

import java.util.*;

public class JsonUtil {
	/**
	 * 该字符串可能转为 JSONObject 或 JSONArray
	 * key可以带双引号，也可以不带；value如果是可以带双引号也可以不带，如果是字符串，必须带双引号
	 * @param string
	 * @return
	 */
	public static boolean isJSON(String string) {
		return ((string != null) && ((("null".equals(string))
				|| ((string.startsWith("[")) && (string.endsWith("]"))) || ((string
				.startsWith("{")) && (string.endsWith("}"))))));
	}

	/**
	 * 该字符串可能转为JSONObject
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isJSONObject(String string) {
		return ((string != null) && ((("null".equals(string)) || ((string
				.startsWith("{")) && (string.endsWith("}"))))));
	}

	/**
	 * 该字符串可能转为 JSONArray
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isJSONArray(String string) {
		return ((string != null) && ((("null".equals(string)) || ((string
				.startsWith("[")) && (string.endsWith("]"))))));
	}

	/**
	 * 函数注释：parseJSON2Map()<br>
	 * 时间：2014-10-28-上午10:50:21<br>
	 * 用途：该方法用于json数据转换为<Map<String, Object>
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static Map<String, Object> parseJSON2Map(String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 最外层解析
		JSONObject json = JSONObject.fromObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			// 如果内层还是数组的话，继续解析
			if (v instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				@SuppressWarnings("unchecked")
				Iterator<JSONObject> it = ((JSONArray) v).iterator();
				while (it.hasNext()) {
					JSONObject json2 = it.next();
					list.add(parseJSON2Map(json2.toString()));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}

	/**
	 * 函数注释：parseJSON2MapString()<br>
	 * 用途：该方法用于json数据转换为<Map<String, String><br>
	 * 备注：***<br>
	 */
	public static Map<String, String> parseJSON2MapString(String jsonStr) {
		Map<String, String> map = new HashMap<String, String>();
		// 最外层解析
		JSONObject json = JSONObject.fromObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			if (null != v) {
				map.put(k.toString(), v.toString());
			}
		}
		return map;
	}

	public static void main(String[] args) {
		String json = "{\"timestamp\":\"1445417311413\",\"nonce\":\"awe\",\"signature\":\"e3eb559331c57e26d9845175b2d99fc7\"}";
		String json2 = "{timestamp:\"1445417311413\",nonce:\"awe\",signature:\"e3eb559331c57e26d9845175b2d99fc7\"}";
//		String json3 = "{timestamp:1445417311413,nonce:awe,signature:e3eb559331c57e26d9845175b2d99fc7}";
		System.out.println(json+"=="+isJSON(json));
		System.out.println(json2+"=="+isJSON(json2));
//		System.out.println(json3+"=="+isJSON(json3));
		System.out.println(parseJSON2MapString(json).get("signature"));
		System.out.println(parseJSON2MapString(json2).get("signature"));
//		System.out.println(parseJSON2MapString(json3).get("signature"));
		
		
	}
	

	public void toJsonArray(){
		String json3="{\"votes\":[{\"id\":45,\"sid\":330,\"title\":\"tetst\",\"isOne\":0,\"type\":1,\"options\":[{\"id\":71,\"name\":\"11\",\"number\":\"1\",\"voteId\":45},{\"id\":72,\"name\":\"122\",\"number\":\"2\",\"voteId\":45}]}" +
				",{\"id\":21,\"createTime\":\"2015-10-28 13:48:49\",\"sid\":1,\"title\":\"234\",\"status\":0,\"isOne\":0,\"type\":0,\"deadTime\":null,\"options\":[{\"total\":0,\"id\":28,\"createTime\":\"2015-10-28 13:48:49\",\"name\":\"22\",\"number\":\"1\",\"voteId\":21},{\"total\":0,\"id\":29,\"createTime\":\"2015-10-28 13:48:49\",\"name\":\"33\",\"number\":\"2\",\"voteId\":21}]}" +
				"]}";
		System.out.println(json3);
		System.out.println("json3="+isJSON(json3));
		
        //将json字符串转成json对象
        JSONObject jb = JSONObject.fromObject(json3);
        //取查询参数votes,是个json数组
        JSONArray jsons = jb.getJSONArray("votes");
        int jsonLength = jsons.size();
        //对json数组进行循环
        for (int i = 0; i < jsonLength; i++) {
            JSONObject tempJson = JSONObject.fromObject(jsons.get(i));
            System.out.println("ddd:" + tempJson);
            String id = StringEscapeUtils.escapeSql(tempJson.getString("id" ));
                      String sid = StringEscapeUtils.escapeSql(tempJson.getString("sid"));
                     String title = StringEscapeUtils.escapeSql(tempJson.getString("title"));
                     String options = StringEscapeUtils.escapeSql(tempJson.getString("options"));
                    
                     System.out.println(id);
                     System.out.println(sid);
                     System.out.println(title);
                     System.out.println(options);
         }
        System.out.println("======================");
        Map<String, Object> map = parseJSON2Map(json3);
        @SuppressWarnings("unchecked")
		List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("votes");
        Map<String, Object> map2 = list.get(0);
        System.out.println(map2.get("id"));
	}
	

	public void jsonToPara(){
		String jsonString="{\"id\":3,\"sex\":\"0\",\"birth\":null,\"height\":null,\"name\":null,\"tel\":\"2\",\"district\":null}";
		jsonString=jsonString.replace("{", "").replace("}", "").replace("\"", "").replace(":", "=").replace(",", "&");
		
		System.out.println(jsonString);
	}

}
