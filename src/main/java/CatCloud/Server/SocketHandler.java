package CatCloud.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;

import CatCloud.Client.onResponceListener;

public class SocketHandler {

	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;
	private Thread thread;

	public SocketHandler(Socket socket) {
		this.socket = socket;
		
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
								//对请求进行解析
								//获取请求类型 查找对应的请求处理器
								System.out.println(line);
								
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

	public void sendMessage(String msg) {
		try {
			writer.write(msg+"\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
