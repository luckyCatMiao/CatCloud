package CatCloud.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;
import java.util.stream.Collectors;

import CatCloud.Server.Handler.BaseServerHandler;
import CatCloud.Server.Handler.BoardCastRequestHandler;
import CatCloud.Server.Handler.GetClientIDHandler;
import CatCloud.Server.Handler.MessageBean;
import CatCloud.Server.Handler.PrivateMessageHandler;
import CatCloud.Server.Handler.RoomHandler.CreateRoomHandler;
import CatCloud.Server.Handler.RoomHandler.EnterRoomHandler;
import CatCloud.Server.Handler.RoomHandler.ExitRoomHandler;

public class Server {


	/**
	 * 是否是开发模式
	 */
	private boolean debug=true;
	
	/**
	 * 保存所有房间
	 */
	private LinkedList<Room> rooms=new LinkedList<>();
	/**
	 * 默认房间
	 */
	private Room defaultRoom=new Room("default");
	/**
	 * 请求处理器
	 */
	private LinkedList<BaseServerHandler> handlers=new LinkedList<>();
	
	
	public Server(int port) {
		
		//添加默认房间
		rooms.add(defaultRoom);
		
		new Thread()
		{
			public void run() 
			{
				try {
					ServerSocket serverSocket = new ServerSocket(port);
					
					while(true)
					{
						Socket socket=serverSocket.accept();
						SocketHandler socketHandler=new SocketHandler(socket,Server.this,defaultRoom);
						//加入默认房间中
						defaultRoom.addClient(socketHandler);

					}
			
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
		}.start();
		
		addDefaultHandler();
		
	}
	
	
	/**
	 * 添加一些默认请求处理器
	 */
	private void addDefaultHandler() {
		
		addHandler(new BoardCastRequestHandler());
		addHandler(new PrivateMessageHandler());
		addHandler(new GetClientIDHandler());
		addHandler(new CreateRoomHandler());
		addHandler(new EnterRoomHandler());
		addHandler(new ExitRoomHandler());
	}


	/**
	 * 添加请求处理器
	 * @param handler
	 */
	public void addHandler(BaseServerHandler handler)
	{
		handlers.add(handler);
	}


	/**
	 * 返回对应类型的处理器
	 * @param bean
	 * @return
	 */
	public List<BaseServerHandler> getHandler(MessageBean bean) {
		
		//返回所有符合条件的处理器

		return handlers.stream().filter(h->h.canHandlerMessage(bean)).collect(Collectors.toList());
	}


	/**
	 * 创建房间
	 * @param roomName
	 */
	public void createRoom(String roomName) {
		Room room=new Room(roomName);
		rooms.add(room);
		
	}


	/**
	 * 离开房间
	 * @param clientID
	 * @param roomName
	 */
	public void exitRoom(int clientID, String roomName) {
		Room room=getRoomByName(roomName);
		room.removeClient(getClientByID(clientID));
	}


	/**
	 * 进入房间
	 * @param clientID
	 * @param roomName
	 */
	public void enterRoom(int clientID, String roomName) {
		
		Room room=getRoomByName(roomName);
		room.addClient(getClientByID(clientID));
		
	}


	/**
	 * 根据id查找client
	 * @param clientID
	 * @return
	 */
	private SocketHandler getClientByID(int clientID) {
		for(SocketHandler handler:defaultRoom.getClients())
		{
			if(handler.getClientID()==clientID)
			{
				return handler;
			}
		}
		
		throw new RuntimeException("client "+clientID+" don't exist!");
	}


	/**
	 * 查找房间
	 * @param roomName
	 * @return
	 */
	private Room getRoomByName(String roomName) {

		for(Room room:rooms)
		{
			if(room.getName().equals(roomName))
			{
				
			}
		}
		
		throw new RuntimeException(roomName+" don't exist!");
	}
	

}
