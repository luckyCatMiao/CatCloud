package CatCloud.Server.Handler;

import org.json.JSONObject;

import CatCloud.Server.SocketHandler;
import CatCloud.Server.TCPRequest;
import CatCloud.Server.TCPResponce;

public abstract class BaseServerHandler {


	/**
	 * 能否处理该信息
	 */
	abstract public boolean canHandlerMessage(MessageBean bean);

	/**
	 * 处理信息
	 * @param request
	 * @param responce
	 */
	abstract public void handlerMessage(TCPRequest request, TCPResponce responce);

	
	
	
	

}
