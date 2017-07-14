package CatCloud.Server.Handler;

import org.json.JSONException;
import org.json.JSONObject;

import CatCloud.Client.Message.Config;
import CatCloud.Server.SocketHandler;
import CatCloud.Server.TCPRequest;
import CatCloud.Server.TCPResponce;

/**
 * 默认的广播消息处理器 处理方式是转发给所有房间里的人
 * @author Administrator
 *
 */
public class BoardCastRequestHandler extends BaseServerHandler{




	@Override
	public boolean canHandlerMessage(MessageBean bean) {
		// TODO Auto-generated method stub
		return bean.getSendType().equals(Config.SEND_TYPE_BOARDCAST);
	}



	@Override
	public void handlerMessage(TCPRequest request, TCPResponce responce) {
		//将传播方式去除
		JSONObject data=request.getRequestData();
		data.remove(Config.KEY_SEND_TYPE);
		//转发
		request.getSocketHandler().sendBoardCast(data.toString());
		//发送默认回应
		responce.defaultResponce();
		
	}

}
