package CatCloud.Server.Message.Responce;

import java.util.HashMap;

import CatCloud.Client.Message.Config;
import CatCloud.Server.Message.ServerMessage;

/**
 * 回应消息基类 id对应一条客户端发来的信息id
 * @author Administrator
 *
 */
public abstract class BaseResponce extends ServerMessage{

	public BaseResponce() {
		super(Config.TYPE_RESPONCE);

	}

	
	
	
}
