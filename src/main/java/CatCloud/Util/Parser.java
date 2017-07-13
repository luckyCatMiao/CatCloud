package CatCloud.Util;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import CatCloud.Message.BaseMessage;

public class Parser {

	/**
	 * 将消息解析成json字符串
	 * @param message
	 * @return
	 */
	public static String parseMsgToJson(BaseMessage message) {
		
		JSONObject jsonObject=new JSONObject();
		HashMap<String, String> map=message.getKeyValue();
		for(String key:map.keySet())
		{
			String value=map.get(key);
			try {
				jsonObject.put(key, value);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return jsonObject.toString();
	}

}
