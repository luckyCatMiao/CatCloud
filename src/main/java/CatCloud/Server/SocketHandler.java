package CatCloud.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import CatCloud.Client.onResponceListener;
import CatCloud.Client.Message.ClientMessage;
import CatCloud.Client.Message.Config;
import CatCloud.Server.Handler.BaseServerHandler;
import CatCloud.Server.Handler.MessageBean;
import CatCloud.Server.Message.ServerMessage;
import CatCloud.Util.ParserUtil;


/**
 * 与客户端socket对应的socket包装类
 * @author Administrator
 *
 */
public class SocketHandler {

	/**
	 * 所在的服务器
	 */
	private Server server;
	/**
	 * 所在的房间
	 */
	private Room room;
	/**
	 * 对应的ID
	 */
	private int clientID;
	
	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;
	private Thread thread;

	public SocketHandler(Socket socket,Server server,Room room) {
		this.socket = socket;
		this.server = server;
		this.room=room;
		
		try {
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),Charset.forName("UTF-8")));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
		} catch (IOException e) {
			
			e.printStackTrace();
			throw new RuntimeException("create socket handler failed!!");
		}
		
		
		initThread();
		//初始化id为hashCode，保证唯一值
		clientID=hashCode();
		
	}

	private void initThread() {
		if(this.thread==null)
		{
			//开启新线程监听响应
			this.thread=new Thread()
			{
				public void run() 
				{

						String line;
						try {
							while((line=reader.readLine())!=null)
							{
								System.out.println("receive from id:"+clientID+",msg:"+line);
								
								//对请求进行解析
								MessageBean bean = null;
								JSONObject jsonObject = null;
								try {
									 jsonObject=new JSONObject(line);
									 bean=ParserUtil.parseJSONToMessageBean(jsonObject);
									 
										
									//根据信息数据 查找处理器
									List<BaseServerHandler> handlers= server.getHandler(bean);
									for(BaseServerHandler handler:handlers)
									{
										//组装成请求对象
										TCPRequest request=new TCPRequest(jsonObject,bean,SocketHandler.this);
										//创建回应对象
										TCPResponce responce=new TCPResponce(jsonObject,bean,SocketHandler.this);
										
										//处理该消息
										handler.handlerMessage(request,responce);
									}
									
									
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									System.err.println("unvalid request data:"+line);
								}
								
								
								
								
								
								
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

				}
			};
			thread.start();
		}
	}

	/**
	 * 发送消息
	 * @param message
	 */
	public void sendMessage(ServerMessage message) {
		//发送消息
		String jsonString=ParserUtil.parseMsgToJson(message);
		
		
		sendMessage(jsonString);
	}

	/**
	 * 向该client发送消息
	 * @param msg
	 */
	public void sendMessage(String msg) {
		System.out.println("send to id:"+clientID+",msg:"+msg);
		
		try {
			writer.write(msg+"\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public int getClientID() {
		return clientID;
	}

	public Room getRoom() {
		return room;
	}

	
	public void sendBoardCast(ServerMessage message) {
		
		getRoom().sendBoardCast(message,clientID);
		
	}

	
	public void sendBoardCast(String msg) {
		
		getRoom().sendBoardCast(msg,clientID);
	}
	
	
}
