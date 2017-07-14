package Main;

import java.util.HashMap;

import org.json.JSONObject;

import CatCloud.Client.Message.ClientMessage;

public class HelloMessage extends ClientMessage{

	
	public HelloMessage() {
		super("hello");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void dealResponce(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addMessageData(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		
	}
}
