package CatCloud.Client.Request.OSMessage;

import java.util.HashMap;

import CatCloud.Client.Request.BaseMessage;
import CatCloud.Client.Request.Config;

/**
 * 跟服务器通信的信息 不会发送给其他客户端 用来进行掉线检测之类的
 * @author Administrator
 *
 */
public abstract class OSMessage extends BaseMessage{

	public OSMessage(String msg) {
		super(Config.TYPE_TOSERVER,msg);

	}

	
	
}
