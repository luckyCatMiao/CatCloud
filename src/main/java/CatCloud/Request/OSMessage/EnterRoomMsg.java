package CatCloud.Request.OSMessage;

import java.util.HashMap;

import CatCloud.Request.Config;

public class EnterRoomMsg extends OSMessage{

	private String roomName;


	public EnterRoomMsg(String roomName) {
		super(Config.MSG_ENTER_ROOM);
		// TODO Auto-generated constructor stub
		this.roomName = roomName;
	}

	
	@Override
	public HashMap<String, String> getKeyValue() {
		HashMap<String, String> map=super.getKeyValue();
		map.put(Config.KEY_ROOMNAME, roomName);
		return map;
	}
	
}
