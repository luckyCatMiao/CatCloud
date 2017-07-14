package CatCloud.Client.Message.OSMessage;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import CatCloud.Client.Message.ClientMessage;
import CatCloud.Client.Message.Config;

public class GetClientIDMessage extends ClientMessage{

	
	
	private int clientID;

	public GetClientIDMessage() {
		super(Config.TYPE_GET_CLIENT_ID);
	
	}

	@Override
	public void dealResponce(JSONObject jsonObject) {
		
		try {
			this.clientID=jsonObject.getInt(Config.KEY_CLIENTID);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getClientID() {
		// TODO Auto-generated method stub
		return clientID;
	}

	@Override
	protected void addMessageData(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		
	}



}
