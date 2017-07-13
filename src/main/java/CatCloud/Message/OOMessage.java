package CatCloud.Message;

import java.util.HashMap;

import CatCloud.Util.Util;

public class OOMessage extends BaseMessage{

	protected int clientID;

	public OOMessage(int clientID, String msg) {
		super(msg);
		this.clientID = clientID;
	}
	
	
	@Override
	public HashMap<String, String> getKeyValue() {
		HashMap<String, String> map=super.getKeyValue();
		map.put(Config.KEY_TYPE, Config.TYPE_OO);
		return map;
	}

}
