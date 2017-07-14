package CatCloud.Server.Message;

import java.util.HashMap;

import CatCloud.Client.Message.Config;
import CatCloud.Util.BaseMessage;

/**
 * 服务器发给客户端的消息基类
 * @author Administrator
 *
 */
public abstract class ServerMessage extends BaseMessage{

	public ServerMessage(String type) {
		super(type);
	}



	
	
	
}
