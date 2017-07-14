package Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;

import CatCloud.Client.Client;
import CatCloud.Client.onResponceListener;
import CatCloud.Client.Message.ClientMessage;
import CatCloud.Client.onResponceListener;

public class ClientMain {

	public static void main(String[] args) {
		
		Client client=new Client("localhost", 8999);
		
		
		System.out.println(client.getClientID());
		
		//test1(client);
		//test2(client);
		//test3(client);
		
		
		//client.addHandler();
		
//		client.sendBoardCast("大家好",new onResponceListener<OBMessage>() {
//
//			@Override
//			public void onResponce(OBMessage responce) {
//				
//				System.out.println("成功2");
//				
//			}
//
//			@Override
//			public void onFailed(OBMessage responce) {
//				System.out.println("失败2");
//				
//			}
//		});
		
		
//		client.createRoom("testRoom",new onResponceListener() {
//			
//			@Override
//			public void onResponce(MessageResponce responce) {
//				System.out.println("创建房间成功");
//				
//				client.enterRoom("testRoom",new onResponceListener() {
//					
//					@Override
//					public void onResponce(MessageResponce responce) {
//						System.out.println("加入房间成功");
//					}
//				});
//				
//			}
//		});
	
		
//		
//		Client client2=new Client("localhost", 8999);
//		client2.addOnResponceListener(new onResponceListener() {
//			
//			@Override
//			public void onResponce(String message) {
//				System.out.println("client2:"+message);
//				
//			}
//		});
//		client2.enterRoom("testRoom");
//		client2.sendBoardCast("你好");
		
			
		
		
	
	
		
	}

	public static void test3(Client client) {
		client.sendToServer(new HelloMessage(),new onResponceListener<HelloMessage>() {

			@Override
			public void onResponce(HelloMessage responce) {

				
			}

			@Override
			public void onFailed(HelloMessage responce) {	
				
			}
		});
	}

	public static void test2(Client client) {
		client.sendToClient(new HelloMessage(),new onResponceListener<HelloMessage>() {

			@Override
			public void onResponce(HelloMessage responce) {
				
				System.out.println("成功");
				
			}

			@Override
			public void onFailed(HelloMessage responce) {
				System.out.println("失败");
				
			}
		}, 666);
	}

	public static void test1(Client client) {
		client.sendBoardCast(new HelloMessage(),new onResponceListener<HelloMessage>() {

			@Override
			public void onResponce(HelloMessage responce) {
				
				System.out.println("成功");
				
			}

			@Override
			public void onFailed(HelloMessage responce) {
				System.out.println("失败");
				
			}
		});
	}
}
