package CatCloud.Client;

import CatCloud.Client.Message.OSMessage.CreateRoomMsg;
import CatCloud.Client.Message.OSMessage.EnterRoomMsg;
import CatCloud.Client.Message.OSMessage.ExitRoomMsg;

public class RoomHelper {

	private Client client;

	public RoomHelper(Client client) {
		this.client = client;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 创建房间
	 * @param string
	 * @param name TODO
	 * @param pListener TODO
	 */
	public void createRoom(String roomName, onResponceListener pListener) {
		
		CreateRoomMsg message=new CreateRoomMsg(roomName);
		client.sendToServer(message,pListener);	
		
	}

	/**
	 * 进入房间
	 * @param string
	 * @param name TODO
	 * @param pListener TODO
	 */
	public void enterRoom(String roomName, onResponceListener pListener) {
		
		EnterRoomMsg message=new EnterRoomMsg(roomName);
		client.sendToServer(message,pListener);	
		
	}
	
	/**
	 * 退出房间
	 * @param string
	 * @param name TODO
	 * @param pListener TODO
	 */
	public void exitRoom(String roomName, onResponceListener pListener) {
		
		ExitRoomMsg message=new ExitRoomMsg(roomName);
		client.sendToServer(message,pListener);	
		
	}
	
	

}
