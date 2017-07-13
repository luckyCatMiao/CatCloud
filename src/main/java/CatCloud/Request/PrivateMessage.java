package CatCloud.Request;

import java.util.HashMap;

import CatCloud.Util.Util;

/**
 * 发给单个client的信息
 * @author Administrator
 *
 */
public class PrivateMessage extends BaseMessage{

	protected int clientID;
	private String message;

	public PrivateMessage(int clientID, String message) {
		super(Config.KEY_TYPE);
		this.clientID = clientID;
		this.message = message;
	}
	
	
	@Override
	public HashMap<String, String> getKeyValue() {
		HashMap<String, String> map=super.getKeyValue();
		map.put(Config.KEY_MSG, message);
		map.put(Config.KEY_CLIENTID, clientID+"");
		return map;
	}

}
