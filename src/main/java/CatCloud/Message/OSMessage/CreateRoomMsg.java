package CatCloud.Message.OSMessage;

import java.util.HashMap;

import CatCloud.Message.Config;

public class CreateRoomMsg extends OSMessage {

	private String roomName;


	public CreateRoomMsg(String roomName) {
		super(Config.MSG_CREATE_ROOM);
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
