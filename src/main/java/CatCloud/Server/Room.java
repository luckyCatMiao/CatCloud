package CatCloud.Server;

import java.util.LinkedList;

import org.json.JSONObject;

import CatCloud.Client.Message.ClientMessage;
import CatCloud.Server.Message.ServerMessage;
import CatCloud.Util.ParserUtil;

/**
 * 服务器房间 模拟游戏中的房间 广播之类的都是房间范围 一个玩家可以在多个房间中
 * 比如一个人又在大厅中 又在某个私人小组中
 * @author Administrator
 *
 */
public class Room {

	/**
	 * 保存所有客户端对应的socket
	 */
	private LinkedList<SocketHandler> sockets=new LinkedList<>();
	
	/**
	 * 房间名字
	 */
	private String name;

	public Room(String name) {
		this.name = name;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 添加客户端到房间中
	 * @param socketHandler
	 */
	public void addClient(SocketHandler socketHandler) {
		sockets.add(socketHandler);
		
	}
	
	
	/**
	 * 移除客户端
	 * @param socketHandler
	 */
	public void removeClient(SocketHandler socketHandler) {
		sockets.remove(socketHandler);
		
	}

	/**
	 * 向房间内的客户端广播信息
	 * @param message
	 */
	public void sendBoardCast(ServerMessage message) {
		
		sendBoardCast(ParserUtil.parseMsgToJson(message));
	}

	/**
	 * 向房间内的客户端直接广播json
	 * @param jsonObject
	 */
	public void sendBoardCast(String json) {

		sockets.forEach(s->s.sendMessage(json));
		
	}

	/**
	 * 给对应ID的客户端发送消息
	 * @param string
	 * @param id
	 */
	public void sendTo(String string, int id) {
		for(SocketHandler handler:sockets)
		{
			if(handler.getClientID()==id)
			{
				handler.sendMessage(string);
				return;
			}
		}
		
		throw new RuntimeException("client id:"+id+" not in the room!!");
		
	}
	
	
	/**
	 *	给对应ID的客户端发送消息
	 * @param message
	 * @param id clientID
	 */
	public void sendTo(ServerMessage message, int id) {
		
		sendTo(ParserUtil.parseMsgToJson(message), id);
		
	}

	/**
	 * 发送广播 除了对应id的client之外
	 * @param message
	 * @param clientID
	 */
	public void sendBoardCast(ServerMessage message, int clientID) {
		
		sendBoardCast(ParserUtil.parseMsgToJson(message), clientID);
		
	}

	/**
	 * 发送广播 除了对应id的client之外
	 * @param message
	 * @param clientID
	 */
	public void sendBoardCast(String msg, int clientID) {
		for(SocketHandler handler:sockets)
		{
			if(handler.getClientID()!=clientID)
			{
				sendTo(msg, handler.getClientID());
			}
		}
		
	}

}
