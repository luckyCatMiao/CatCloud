package CatCloud.Client.Message.OSMessage;

import java.util.HashMap;

import CatCloud.Client.Message.ClientMessage;
import CatCloud.Client.Message.Config;

/**
 * 跟服务器通信的信息 不会发送给其他客户端 用来进行掉线检测之类的
 * @author Administrator
 *
 */
public abstract class OSMessage extends ClientMessage{

	public OSMessage(String msg) {
		super(Config.SEND_TYPE_TOSERVER,msg);

	}

	
	
}
