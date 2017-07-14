package CatCloud.Client;

import CatCloud.Client.Message.ClientMessage;

public interface onResponceListener<T extends ClientMessage> {

	/**
	 * 回应(只是服务器发回了响应 不代表响应是正确的)
	 * @param responce
	 */
	void onResponce(T message);
	
	/**
	 * 发送失败 服务器没有响应(也不代表信息没有到达服务器 可能只是服务器没有回应)
	 * @param message
	 */
	void onFailed(T message);
}
