package CatCloud.Server.Handler;

import org.json.JSONObject;

import CatCloud.Server.Room;

public abstract class BaseRequestHandler {


	/**
	 * 处理信息
	 * @param jsonObject
	 * @param room
	 */
	abstract public void handlerMessage(JSONObject jsonObject,MessageBean bean, Room room);

	/**
	 * 能否处理该信息
	 */
	abstract public boolean canHandlerMessage(MessageBean bean);
	
	
	
	

}
