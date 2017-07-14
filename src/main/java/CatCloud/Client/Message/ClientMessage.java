package CatCloud.Client.Message;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import CatCloud.Util.BaseMessage;


/**
 * 客户端发给服务器的消息基类
 * @author Administrator
 *
 */
public abstract class ClientMessage extends BaseMessage{

	/**
	 * 信息传播类型
	 */
	protected String sendType;
	
	
	public ClientMessage(String type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public HashMap<String, String> getKeyValue() {
		
		HashMap<String, String> map=super.getKeyValue();
		//添加额外的传播类型数据
		//注意这里没有覆盖抽象方法 而是重写了这个方法 因为抽象方法必须留到子类实现
		//否则子类可能会忘了实现
		map.put(Config.KEY_SEND_TYPE, sendType);
		return map;
	}

	

	
	public String getSendType() {
		return sendType;
	}


	public void setSendType(String sendType) {
		this.sendType = sendType;
	}


	/**
	 * 处理响应
	 * @param jsonObject
	 */
	abstract public void dealResponce(JSONObject jsonObject);

	
	
	
	
	
}
