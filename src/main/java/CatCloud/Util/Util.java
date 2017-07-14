package CatCloud.Util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import CatCloud.Client.Message.Config;
import CatCloud.Server.Handler.MessageBean;

public class Util {

	/**
	 * 将object的fields解析为map
	 * @param object 对象
	 * @param ignores 需要忽略的键
	 * @return
	 */
	public static HashMap<String, String> parseFieldToMap(Object object, String[] ignores) {
		
		if(ignores==null)
		{
			ignores=new String[]{};
		}
		
		List<String> strings=new ArrayList<>(Arrays.asList(ignores));
		HashMap<String, String> map=new HashMap<>();
		
		Field[] fields=object.getClass().getDeclaredFields();
		
		for(Field field:fields)
		{
			field.setAccessible(true);
			String fieldName=field.getName();
			try {
				String fieldValue=field.get(object)==null?null:field.get(object).toString();
				if(!strings.contains(fieldName))
				{
					map.put(fieldName, fieldValue);
				}
				
			} catch (IllegalAccessException | IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		System.out.println(map);
		
		return map;
	}

	public static MessageBean parseJSONToMessageBean(JSONObject jsonObject) {
		
		try {
			String msgType=jsonObject.getString(Config.KEY_MSG_TYPE);
			String sendType=jsonObject.getString(Config.KEY_SEND_TYPE);
			int id=Integer.parseInt(jsonObject.getString(Config.KEY_ID));
			
			return new MessageBean(msgType,sendType,id);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}

}
