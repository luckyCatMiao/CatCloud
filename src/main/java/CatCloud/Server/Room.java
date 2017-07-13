package CatCloud.Server;

import java.util.LinkedList;

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
	
	private String name;

	public Room(String name) {
		this.name = name;
		// TODO Auto-generated constructor stub
	}

	public void addClient(SocketHandler socketHandler) {
		sockets.add(socketHandler);
		
	}

}