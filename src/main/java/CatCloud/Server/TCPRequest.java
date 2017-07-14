package CatCloud.Server;

import org.json.JSONObject;

import CatCloud.Server.Handler.MessageBean;

public class TCPRequest {

	private JSONObject jsonObject;
	private MessageBean bean;
	private SocketHandler socketHandler;

	public TCPRequest(JSONObject jsonObject, MessageBean bean, SocketHandler socketHandler) {
		this.jsonObject = jsonObject;
		this.bean = bean;
		this.socketHandler = socketHandler;
		// TODO Auto-generated constructor stub
	}

	public JSONObject getRequestData() {
		return jsonObject;
	}

	public MessageBean getRequestMessage() {
		return bean;
	}

	public SocketHandler getSocketHandler() {
		return socketHandler;
	}


	
	
	
}
