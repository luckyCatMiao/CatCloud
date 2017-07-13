package CatCloud.Server.Handler;

public class MessageBean {

	private String msgType;
	private String sendType;
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
