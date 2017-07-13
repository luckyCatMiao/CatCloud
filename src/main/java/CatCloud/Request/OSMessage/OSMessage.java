package CatCloud.Request.OSMessage;

import java.util.HashMap;

import CatCloud.Request.BaseMessage;
import CatCloud.Request.Config;

/**
 * 跟服务器通信的信息 不会发送给其他客户端 用来进行掉线检测之类的
 * @author Administrator
 *
 */
public abstract class OSMessage extends BaseMessage{

	public OSMessage(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public HashMap<String, String> getKeyValue() {
		HashMap<String, String> map=super.getKeyValue();
		map.put(Config.KEY_TYPE, Config.TYPE_TOSERVER);
		return map;
	}
}
