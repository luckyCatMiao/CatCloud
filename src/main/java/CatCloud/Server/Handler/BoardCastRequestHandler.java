package CatCloud.Server.Handler;

import org.json.JSONException;
import org.json.JSONObject;

import CatCloud.Client.Request.Config;
import CatCloud.Server.Room;

/**
 * 默认的广播消息处理器 处理方式是转发给所有房间里的人
 * @author Administrator
 *
 */
public class BoardCastRequestHandler extends BaseRequestHandler{




	@Override
	public boolean canHandlerMessage(MessageBean bean) {
		// TODO Auto-generated method stub
		return bean.getSendType().equals(Config.SEND_TYPE_BOARDCAST);
	}

	@Override
	public void handlerMessage(JSONObject jsonObject, MessageBean bean, Room room) 
	{
		
		System.out.println(jsonObject);
		//将传播方式去除
		jsonObject.remove(Config.KEY_SEND_TYPE);
		room.boardCast(jsonObject.toString());
		
	}

}
