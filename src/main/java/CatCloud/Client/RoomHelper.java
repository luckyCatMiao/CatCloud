package CatCloud.Client;

import CatCloud.Client.Message.OSMessage.CreateRoomMsg;
import CatCloud.Client.Message.OSMessage.EnterRoomMsg;
import CatCloud.Client.Message.OSMessage.OSMessage;

public class RoomHelper {

	/**
	 * 创建房间
	 * @param string
	 * @param client TODO
	 * @param name TODO
	 * @param pListener TODO
	 */
	public void createRoom(Client client, String name, onResponceListener pListener) {
		
		OSMessage message=new CreateRoomMsg(name);
		//发送回调消息
		client.sendMessage(message,pListener);	
		
	}

	/**
	 * 进入房间
	 * @param string
	 * @param client TODO
	 * @param name TODO
	 * @param listener TODO
	 */
	public void enterRoom(Client client, String name, onResponceListener listener) {
		
		OSMessage message=new EnterRoomMsg(name);
		client.sendMessage(message,listener);
		
	}

}
