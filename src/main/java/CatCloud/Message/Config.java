package CatCloud.Message;

public class Config {

	/**
	 * 协议内部键值
	 */
	final static public String KEY_TYPE="type";
	final public static  String KEY_MSG = "msg";
	public static final String KEY_ID = "id";
	public static final String KEY_ROOMNAME = "roomName";

	/**
	 * 消息类型
	 * 客户端单对单通信
	 */
	final static public String TYPE_OO="1";
	/**
	 * 客户端广播
	 */
	final static public String TYPE_OB="2";
	/**
	 * 客户端服务器内部通信
	 */
	final static public String TYPE_OS="3";
	/**
	 * 对某信息的回应消息
	 */
	final static public String TYPE_SR="4";
	
	
	/**
	 * 协议内部信息值
	 */
	final static public String MSG_CREATE_ROOM="100";
	final static public String MSG_ENTER_ROOM="105";

	


	
}
