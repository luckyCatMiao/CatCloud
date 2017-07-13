package CatCloud.Message;

import java.util.HashMap;

import CatCloud.Util.Util;

public class OBMessage extends BaseMessage{

	

	
	
	public OBMessage(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	@Override
	public HashMap<String, String> getKeyValue() {
		HashMap<String, String> map=super.getKeyValue();
		map.put(Config.KEY_TYPE, Config.TYPE_OB);
		return map;
	}


	
}
