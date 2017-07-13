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

import org.json.JSONException;
import org.json.JSONObject;

import CatCloud.Message.BaseMessage;
import CatCloud.Message.Config;
import CatCloud.Message.MessageResponce;
import CatCloud.Message.OBMessage;
import CatCloud.Message.OOMessage;
import CatCloud.Message.OSMessage.CreateRoomMsg;
import CatCloud.Message.OSMessage.EnterRoomMsg;
import CatCloud.Message.OSMessage.OSMessage;
import CatCloud.Util.Parser;

public class Client {

	private String ip;
	private int port;
	private onResponceListener listener;
	private BufferedWriter writer;
	private BufferedReader reader;
	private Socket socket;
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
	 * 消息回应
	 */
	private LinkedList<MessageResponce> responces=new LinkedList<>();
	
	public Client(String ip, int port,onResponceListener listener) {
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
		
		
		addOnResponceListener(listener);
	}


	/**
	 * 一对一发送消息
	 * @param msg
	 */
	public void sendMessage(String msg,int clientID) {
		
		//创建一对一消息
		OOMessage message=new OOMessage(clientID,msg);
		//将消息解析成json字符串
		String jsonString=Parser.parseMsgToJson(message);
		//发送
		send(jsonString);
		
	}


	public void send(String jsonString) {
		
		System.out.println("send:"+jsonString);
		
		try {
			writer.write(jsonString+"\n");
			writer.flush();
		} catch (IOException e) {
			throw new RuntimeException("send msg failed!!");
		}
	}
	
	
	/**
	 * 发送广播 对同一个房间里的人有效
	 * @param msg
	 */
	public void sendBoardCast(String msg) {
		
		//创建广播消息
		OBMessage message=new OBMessage(msg);
		//将消息解析成json字符串
		String jsonString=Parser.parseMsgToJson(message);
		//发送
		send(jsonString);
		
	}

	private void addOnResponceListener(onResponceListener listener) {
		initThread(listener);
		this.listener = listener;
	}


	/**
	 * 初始化监听线程
	 * @param listener
	 */
	private void initThread(onResponceListener listener) {
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
//								//对消息进行内部处理
//								dealMessage(line);
								
								if(listener!=null)
								{
									listener.onResponce(line);
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


	protected void dealMessage(String message) {		
		//如果是响应消息
		try {
			JSONObject jsonObject=new JSONObject(message);
			if(jsonObject.getString(Config.KEY_TYPE).equals(Config.TYPE_SR))
			{
				String msg=jsonObject.getString(Config.KEY_MSG);
				String id=jsonObject.getString(Config.KEY_ID);
				
				MessageResponce responce=new MessageResponce(msg,id);
				
				responces.add(responce);
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
	public void createRoom(String name) {
		
		OSMessage message=new CreateRoomMsg(name);
		String jsonString=Parser.parseMsgToJson(message);
		send(jsonString);
//		//等待回应
//		boolean result=waitResponce(message.getId());
//		if(result==false)
//		{
//			throw new RuntimeException("create Room Failed!!!");
//		}
	}

//
//	/**
//	 * 等待某信息响应
//	 * @param id
//	 * @return
//	 */
//	private boolean waitResponce(int id) {
//		
//		return false;
//	}


	/**
	 * 进入房间
	 * @param string
	 */
	public void enterRoom(String name) {
		
		OSMessage message=new EnterRoomMsg(name);
		String jsonString=Parser.parseMsgToJson(message);
		send(jsonString);
		
	}


	public int getMaxTime() {
		return maxTime;
	}


	public void setMaxTime(int maxTime) {
		this.maxTime = maxTime;
	}

	
	
	
}
