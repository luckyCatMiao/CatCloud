package CatCloud.Client;

import CatCloud.Client.Message.ClientMessage;

public interface onResponceListener<T extends ClientMessage> {

	/**
	 * 回应
	 * @param responce
	 */
	void onResponce(T message);
	
	/**
	 * 发送失败 服务器没有响应
	 * @param message
	 */
	void onFailed(T message);
}
