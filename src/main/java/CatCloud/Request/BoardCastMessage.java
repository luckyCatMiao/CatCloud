package CatCloud.Request;

import java.util.HashMap;

import CatCloud.Util.Util;

public class BoardCastMessage extends BaseMessage{

	/**
	 * 需要广播的消息
	 */
	private String message;

	public BoardCastMessage(String message) {
		super(Config.TYPE_BOARDCAST);
		// TODO Auto-generated constructor stub
		this.message = message;
	}

	@Override
	public HashMap<String, String> getKeyValue() {
		HashMap<String, String> map=super.getKeyValue();
		map.put(Config.KEY_MSG,message);
		return map;
	}


	
}
