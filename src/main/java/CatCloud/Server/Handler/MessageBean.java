package CatCloud.Server.Handler;

public class MessageBean {

	/**
	 * 信息类型
	 */
	private String msgType;
	/**
	 * 传播类型
	 */
	private String sendType;
	/**
	 * 信息id
	 */
	private int id;

	public MessageBean(String msgType, String sendType, int id) {
		this.msgType = msgType;
		this.sendType = sendType;
		this.id = id;
		// TODO Auto-generated constructor stub
	}

	public String getMsgType() {
		return msgType;
	}

	public String getSendType() {
		return sendType;
	}

	public int getId() {
		return id;
	}

	
	
}
