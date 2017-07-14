package Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

import CatCloud.Server.Server;

public class ServerMain {

	public static void main(String[] args) {
		
		
		Server server=new Server(8999);
		server.addHandler(new ServerHelloHandler());
	
	
	}
}
