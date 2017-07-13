package CatCloud.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.LinkedList;

public class Server {


	/**
	 * 保存所有房间
	 */
	private LinkedList<Room> rooms=new LinkedList<>();
	/**
	 * 默认房间
	 */
	private Room defaultRoom=new Room("default");
	
	
	public Server(int port) {
		
		//添加默认房间
		rooms.add(defaultRoom);
		
		
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			
			while(true)
			{
				Socket socket=serverSocket.accept();
				SocketHandler socketHandler=new SocketHandler(socket);
				//加入默认房间中
				defaultRoom.addClient(socketHandler);

			}
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
