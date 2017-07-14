package CatCloud.Client.Message.OSMessage;

import java.util.HashMap;

import org.json.JSONObject;

import CatCloud.Client.Message.ClientMessage;
import CatCloud.Client.Message.Config;

public class EnterRoomMsg extends ClientMessage{

	private String roomName;


	public EnterRoomMsg(String roomName) {
		super(Config.TYPE_ENTER_ROOM);
		
		this.roomName = roomName;
	}


	@Override
	public void dealResponce(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void addMessageData(HashMap<String, String> map) {
		map.put(Config.KEY_ROOMNAME, roomName);
		
	}

	
	
	
}
