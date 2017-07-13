package CatCloud.Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;

import CatCloud.Client.Request.BaseMessage;
import CatCloud.Client.Request.BoardCastMessage;
import CatCloud.Client.Request.Config;
import CatCloud.Client.Request.PrivateMessage;
import CatCloud.Client.Request.OSMessage.CreateRoomMsg;
import CatCloud.Client.Request.OSMessage.EnterRoomMsg;
import CatCloud.Client.Request.OSMessage.OSMessage;
import CatCloud.Util.ParserUtil;
import Main.HelloMessage;

public class Client {

	private class onIDResponceListener
	{
		private int msgID;
		/**
		 * 该监听器的存活时间
		 */
		private int lifeTime;
		/**
		 * 消息回应的监听器
		 */
		private onResponceListener pListener;
		/**
		 * 对应的消息
		 */
		private BaseMessage message;

		public onIDResponceListener(int id, int maxTime, onResponceListener pListener, BaseMessage message) {
			this.msgID = id;
			// TODO Auto-generated constructor stub
			this.lifeTime = maxTime;
			this.pListener = pListener;
			this.message = message;
		}		
	}
	
	
	/**
	 * 服务器IP地址
	 */
	private String ip;
	/**
	 * 端口号
	 */
	private int port;
	/**
	 * 输入输出
	 */
	private BufferedWriter writer;
	private BufferedReader reader;
	private Socket socket;
	/**
	 * 输入监听线程
	 */
	private Thread thread;
	/**
	 * 服务器端的客户端编号
	 */
	private int ClientID;
	/**
	 * 响应超时时间
	 */
	private int maxTime=1000;
	/**
	 * 消息回应监听器
	 */
	private List<onIDResponceListener> listeners=new LinkedList<>();
	
	
	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
	
		try {
			socket = new Socket(ip, port);
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),Charset.forName("UTF-8")));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
		
		} catch (IOException e) {
			
			e.printStackTrace();
			throw new RuntimeException("create socket failed!!");
		}
		
		
		//初始化监听输入线程
		initOnResponceThread();
		//初始化监听消息超时线程
		initReduceLifeThread();
	}


	private void initReduceLifeThread() {
		
		new Thread(){
			@Override
			public void run() {
				
				while(true)
				{
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//减少50ms生存时间
					for(onIDResponceListener listener:listeners)
					{
						
						//System.out.println(listener.lifeTime);
						listener.lifeTime-=50;
						//调用生存时间小于0的监听器 调用发送失败
						if(listener.lifeTime<=0)
						{
							listener.pListener.onFailed(listener.message);
						}
					}
					
					
					
					
					//生存时间小于0的监听器移除(防止内存溢出)
					listeners=listeners.stream().filter(l->l.lifeTime>0).collect(Collectors.toList());
					
				}
				
			}
		}.start();
		
	}


	/**
	 * 发送一对一消息
	 * @param msg
	 */
	public void sendToClient(String msg,int clientID,onResponceListener listener) {
		
		//创建一对一消息
		PrivateMessage message=new PrivateMessage(clientID,msg);
		sendMessage(message,listener);
		
	}
	
	

	/**
	 * 发送一条消息 不进行回调
	 * @param message
	 */
	public void sendMessage(BaseMessage message) {
		
		sendMessage(message, null);
	}


	private void sendString(String jsonString) {
		
		System.out.println("send:"+jsonString);
		
		try {
			writer.write(jsonString+"\n");
			writer.flush();
		} catch (IOException e) {
			throw new RuntimeException("send msg failed!!");
		}
	}
	
	
	/**
	 * 发送广播消息 对同一个房间里的人有效
	 * @param helloMessage
	 */
	public void sendBoardCast(BaseMessage message,onResponceListener listener) {
		
		//设置为广播类型
		message.setSendType(Config.SEND_TYPE_BOARDCAST);
		//发送消息到服务器
		sendMessage(message,listener);
		
	}




	/**
	 * 初始化监听线程
	 * @param listener
	 */
	private void initOnResponceThread() {
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
								//对消息进行处理
								dealMessage(line);

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


	private void dealMessage(String message) {		
		//如果是对某条信息的回应消息
		try {
			JSONObject jsonObject=new JSONObject(message);
			if(jsonObject.getString(Config.KEY_MSG_TYPE).equals(Config.TYPE_RESPONCE))
			{
				
				int id=Integer.parseInt(jsonObject.getString(Config.KEY_ID));
				
				//查找对应的监听器
				for(onIDResponceListener listener:listeners)
				{
					if(listener.msgID==id)
					{
						listener.pListener.onResponce(listener.message);
						
					}
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	/**
	 * 创建房间
	 * @param string
	 */
	public void createRoom(String name,onResponceListener pListener) {
		
		OSMessage message=new CreateRoomMsg(name);
		//发送回调消息
		sendMessage(message,pListener);	
		
	}




	/**
	 * 发送消息到服务器
	 * @param message
	 * @param pListener
	 */
	public void sendMessage(BaseMessage message, onResponceListener pListener) {
		
		//添加回调监听
		onIDResponceListener listener=new onIDResponceListener(message.getId(),maxTime,pListener,message);
		listeners.add(listener);
		//发送消息
		String jsonString=ParserUtil.parseMsgToJson(message);
		sendString(jsonString);
		
	}


	/**
	 * 进入房间
	 * @param string
	 */
	public void enterRoom(String name,onResponceListener listener) {
		
		OSMessage message=new EnterRoomMsg(name);
		sendMessage(message,listener);
		
	}


	public int getMaxTime() {
		return maxTime;
	}


	public void setMaxTime(int maxTime) {
		this.maxTime = maxTime;
	}

	
	
	
}
