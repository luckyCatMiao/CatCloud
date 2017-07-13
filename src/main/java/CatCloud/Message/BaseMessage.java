package CatCloud.Message;

import java.util.HashMap;

import CatCloud.Util.Util;

public abstract class BaseMessage {

	
	protected String msg;
	protected int id;

	public BaseMessage(String msg) {
		this.msg = msg;
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
		map.put("msg", msg);
		map.put("id", id+"");
		
		return map;
	}


	public int getId() {
		return id;
	}
	
	
		

	
	
	
	
}
