package CatCloud.Server.Message.Responce;

import java.util.HashMap;

import CatCloud.Client.Message.Config;
import CatCloud.Server.Message.ServerMessage;

public class GetClientIDResponce extends BaseResponce{

	private int clientID;


	public GetClientIDResponce(int clientID) {
	
		this.clientID = clientID;
	}


	@Override
	protected void addMessageData(HashMap<String, String> map) {
		map.put(Config.KEY_CLIENTID, clientID+"");
		
	}

	



	

}
