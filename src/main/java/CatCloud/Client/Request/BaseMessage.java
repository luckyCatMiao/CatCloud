package CatCloud.Client.Request;

import java.util.HashMap;

import CatCloud.Util.Util;

/**
 * 消息基类
 * @author Administrator
 *
 */
public abstract class BaseMessage {

	/**
	 * 信息传播类型
	 */
	protected String sendType;
	/**
	 * 信息ID
	 */
	protected int id;
	/**
	 * 附带消息
	 */
	protected String message;
	/**
	 * 信息处理类型
	 */
	protected String type;
	
	public BaseMessage() {
		//生成随机的键值代表信息ID
		this.id=hashCode();

	}
	
	
	/**
	 * 返回需要发送的键值对数据
	 * @return
	 */
	public HashMap<String, String> getKeyValue()
	{
		HashMap<String, String> map=new HashMap<>();
		map.put(Config.KEY_SEND_TYPE, sendType);
		map.put(Config.KEY_ID, id+"");
		map.put(Config.KEY_MSG,message);
		map.put(Config.KEY_MSG_TYPE, type);
		
		return map;
	}


	public int getId() {
		return id;
	}


	public String getType() {
		return sendType;
	}


	public void setSendType(String sendType) {
		this.sendType = sendType;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getSendType() {
		return sendType;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	
	
	
	
}
