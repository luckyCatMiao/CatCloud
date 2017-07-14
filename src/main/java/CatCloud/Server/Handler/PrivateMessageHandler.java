package CatCloud.Server.Handler;

import org.json.JSONException;
import org.json.JSONObject;

import CatCloud.Client.Message.Config;
import CatCloud.Server.TCPRequest;
import CatCloud.Server.TCPResponce;

/**
 * 默认的单播处理器
 * @author Administrator
 *
 */
public class PrivateMessageHandler extends BaseServerHandler{

	

	@Override
	public boolean canHandlerMessage(MessageBean bean) {
		// TODO Auto-generated method stub
		return bean.getSendType().equals(Config.SEND_TYPE_TOONE);
	}

	@Override
	public void handlerMessage(TCPRequest request, TCPResponce responce) {

		//将传播方式去除
		JSONObject jsonObject=request.getRequestData();
		jsonObject.remove(Config.KEY_SEND_TYPE);
		int id = 0;
		try {
			id = jsonObject.getInt(Config.KEY_MSG);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//将目标ID去除
		jsonObject.remove(Config.KEY_MSG);
		//转发
		request.getSocketHandler().getRoom().sendTo(jsonObject.toString(),id);
		//默认回应
		responce.defaultResponce();
		
	}

}
