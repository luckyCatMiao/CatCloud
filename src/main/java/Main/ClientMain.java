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

public class ClientMain {

	public static void main(String[] args) {
		
		Client client=new Client("localhost", 8999,new onResponceListener() {
			
			@Override
			public void onResponce(String message) {
				System.out.println("client1:"+message);
				
			}
		});
		
		client.createRoom("testRoom");
		client.enterRoom("testRoom");
		
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
}
