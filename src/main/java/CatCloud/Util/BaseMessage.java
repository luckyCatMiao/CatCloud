package CatCloud.Util;

import java.util.HashMap;

import CatCloud.Client.Message.Config;

/**
 * 可以传播的消息基类
 * @author Administrator
 *
 */
public abstract class BaseMessage {
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
	
	public BaseMessage(String type) {
		//生成随机的键值代表信息ID
		this.id=hashCode();
		this.type=type;
	}
	
	

	/**
	 * 返回需要发送的键值对数据
	 * @return
	 */
	public HashMap<String, String> getKeyValue()
	{
		HashMap<String, String> map=new HashMap<>();
		map.put(Config.KEY_ID, id+"");
		map.put(Config.KEY_MSG,message);
		map.put(Config.KEY_MSG_TYPE, type);
		
		addMessageData(map);
		return map;
	}
	
	

	/**
	 * 子类添加额外的需要传播的数据
	 * @param map
	 * @return
	 */
	protected abstract void addMessageData(HashMap<String, String> map);



	public int getId() {
		return id;
	}


	public String getType() {
		return type;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setType(String type) {
		this.type = type;
	}
}
