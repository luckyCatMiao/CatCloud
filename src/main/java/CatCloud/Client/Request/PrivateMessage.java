package CatCloud.Client.Request;

import java.util.HashMap;

import CatCloud.Util.Util;

/**
 * 发给单个client的信息
 * @author Administrator
 *
 */
public class PrivateMessage extends BaseMessage{

	protected int clientID;

	public PrivateMessage(int clientID, String message) {
		super(Config.KEY_SEND_TYPE,message);
		this.clientID = clientID;
	}
	
	
	@Override
	public HashMap<String, String> getKeyValue() {
		HashMap<String, String> map=super.getKeyValue();
		map.put(Config.KEY_CLIENTID, clientID+"");
		return map;
	}

}
