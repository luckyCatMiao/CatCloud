package CatCloud.Client.Message.OSMessage;

import java.util.HashMap;

import org.json.JSONObject;

import CatCloud.Client.Message.ClientMessage;
import CatCloud.Client.Message.Config;

public class CreateRoomMsg extends ClientMessage {

	private String roomName;


	public CreateRoomMsg(String roomName) {
		super(Config.TYPE_CREATE_ROOM);
		// TODO Auto-generated constructor stub
		this.roomName = roomName;
	}

	

	@Override
	public void dealResponce(JSONObject jsonObject) {
		
		
	}


	@Override
	protected void addMessageData(HashMap<String, String> map) {
		map.put(Config.KEY_ROOMNAME, roomName);
		
	}
	
}
