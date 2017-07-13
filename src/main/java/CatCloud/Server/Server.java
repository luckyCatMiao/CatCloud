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

	private int port;
	private ServerSocket serverSocket;

	/**
	 * 保存所有客户端对应的socket
	 */
	private LinkedList<SocketHandler> sockets=new LinkedList<>();
	
	public Server(int port) {
		this.port = port;
		
		
		try {
			serverSocket = new ServerSocket(port);
			
			while(true)
			{
				Socket socket=serverSocket.accept();
				
				SocketHandler socketHandler=new SocketHandler(socket);
				sockets.add(socketHandler);

			}
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
