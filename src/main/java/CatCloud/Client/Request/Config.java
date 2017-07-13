package CatCloud.Client.Request;

public class Config {

	/**
	 * 协议内部键值
	 */
	/**
	 * 信息发送类型
	 */
	final static public String KEY_SEND_TYPE="sendType";
	final static public String KEY_MSG_TYPE="msgType";
	final public static  String KEY_MSG = "msg";
	public static final String KEY_ID = "id";
	public static final String KEY_ROOMNAME = "roomName";
	public static final String KEY_CLIENTID = "clientID";

	
	
	/**
	 * 信息发送类型(由服务器读取处理)
	 * 客户端单对单通信
	 */
//	final static public String SEND_TYPE_TOONE="toone";
	/**
	 * 客户端广播
	 */
	final static public String SEND_TYPE_BOARDCAST="boardcast";
//	/**
//	 * 客户端服务器内部通信
//	 */
//	final static public String TYPE_TOSERVER="toserver";
//	/**
//	 * 对某信息的回应消息
//	 */
//	final static public String TYPE_RESPONCE="responce";
//	/**
//	 * 服务器传来的系统消息
//	 */
//	final static public String TYPE_SYSTEM="system";
//	
	
	
	/**
	 * 信息类型(默认类型)
	 */
	public static final String TYPE_RESPONCE = "responce";
	
	
	
	/**
	 * 协议内部信息值
	 */
	final static public String MSG_CREATE_ROOM="create_room";
	final static public String MSG_ENTER_ROOM="enter_room";
	
	
	

	


	
}
