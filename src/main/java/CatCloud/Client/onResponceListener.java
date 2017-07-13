package CatCloud.Client;

import CatCloud.Request.BaseMessage;

public interface onResponceListener<T extends BaseMessage> {

	/**
	 * 回应
	 * @param responce
	 */
	void onResponce(T responce);
	
	/**
	 * 发送失败 服务器没有响应
	 * @param responce
	 */
	void onFailed(T responce);
}
