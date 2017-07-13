package CatCloud.Request;

public class Config {

	/**
	 * 协议内部键值
	 */
	final static public String KEY_TYPE="type";
	final public static  String KEY_MSG = "msg";
	public static final String KEY_ID = "id";
	public static final String KEY_ROOMNAME = "roomName";
	public static final String KEY_CLIENTID = "clientID";
	/**
	 * 消息类型(大类)
	 * 客户端单对单通信
	 */
	final static public String TYPE_TOONE="toone";
	/**
	 * 客户端广播
	 */
	final static public String TYPE_BOARDCAST="boardcast";
	/**
	 * 客户端服务器内部通信
	 */
	final static public String TYPE_TOSERVER="toserver";
	/**
	 * 对某信息的回应消息
	 */
	final static public String TYPE_RESPONCE="responce";
	/**
	 * 服务器传来的系统消息
	 */
	final static public String TYPE_SYSTEM="system";
	
	/**
	 * 协议内部信息值
	 */
	final static public String MSG_CREATE_ROOM="create_room";
	final static public String MSG_ENTER_ROOM="enter_room";
	

	


	
}
