package CatCloud.Server;

import java.io.BufferedWriter;
import java.io.IOException;

import org.json.JSONObject;

import CatCloud.Client.Message.ClientMessage;
import CatCloud.Server.Handler.MessageBean;
import CatCloud.Server.Message.ServerMessage;
import CatCloud.Server.Message.Responce.BaseResponce;
import CatCloud.Server.Message.Responce.DefaultResponce;
import CatCloud.Util.ParserUtil;

public class TCPResponce {

	

	private JSONObject jsonObject;
	private MessageBean bean;
	private SocketHandler socketHandler;


	public TCPResponce(JSONObject jsonObject, MessageBean bean, SocketHandler socketHandler) {
		this.jsonObject = jsonObject;
		this.bean = bean;
		this.socketHandler = socketHandler;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 发送消息
	 * @param message
	 */
	public void sendResponce(BaseResponce responce) {
		//设置id
		responce.setId(bean.getId());

		socketHandler.sendMessage(responce);
	}

	/**
	 * 向该client发送消息
	 * @param msg
	 */
	public void sendString(String msg) {
		socketHandler.sendString(msg);
		
	}


	
	public void defaultResponce() {

		sendResponce(new DefaultResponce());
		
	}

}
