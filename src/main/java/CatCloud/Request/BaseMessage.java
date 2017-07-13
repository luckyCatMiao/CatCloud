package CatCloud.Request;

import java.util.HashMap;

import CatCloud.Util.Util;

/**
 * 消息基类
 * @author Administrator
 *
 */
public abstract class BaseMessage {

	/**
	 * 信息类型
	 */
	protected String type;
	/**
	 * 信息ID
	 */
	protected int id;

	public BaseMessage(String type) {
		this.type = type;
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
		map.put(Config.KEY_MSG, type);
		map.put(Config.KEY_ID, id+"");
		
		return map;
	}


	public int getId() {
		return id;
	}


	public String getType() {
		return type;
	}
	
	
	

	
	
	
	
}
