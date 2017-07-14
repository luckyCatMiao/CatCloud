package CatCloud.Util;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import CatCloud.Client.Message.ClientMessage;
import CatCloud.Client.Message.Config;
import CatCloud.Server.Handler.MessageBean;

public class ParserUtil {

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
