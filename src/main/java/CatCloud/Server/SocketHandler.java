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
import CatCloud.Client.Request.BaseMessage;
import CatCloud.Client.Request.Config;
import CatCloud.Server.Handler.BaseRequestHandler;
import CatCloud.Server.Handler.MessageBean;
import CatCloud.Server.Message.ResponceMessage;
import CatCloud.Util.ParserUtil;
import CatCloud.Util.Util;

public class SocketHandler {

	/**
	 * 所在的服务器
	 */
	private Server server;
	/**
	 * 所在的房间
	 */
	private Room room;
	
	
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
								System.out.println("receive:"+line);
								
								//对请求进行解析
								MessageBean bean = null;
								JSONObject jsonObject = null;
								try {
									 jsonObject=new JSONObject(line);
									 bean=Util.parseJSONToMessageBean(jsonObject);
									 
									//发送一条默认的回应消息
									sendMessage(new ResponceMessage(bean.getId()));
										
									//根据信息数据 查找处理器
									List<BaseRequestHandler> handlers= server.getHandler(bean);
									for(BaseRequestHandler handler:handlers)
									{
											//处理该消息
										handler.handlerMessage(jsonObject,bean, room);
									}
									
									
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
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

	protected void sendMessage(BaseMessage message) {
		//发送消息
		String jsonString=ParserUtil.parseMsgToJson(message);
		
		System.out.println("send:"+jsonString);
		sendString(jsonString);
	}

	/**
	 * 向该client发送消息
	 * @param msg
	 */
	public void sendString(String msg) {
		try {
			writer.write(msg+"\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	
	
	
}
