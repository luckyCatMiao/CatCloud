package Main;

import org.json.JSONObject;

import CatCloud.Client.Message.Config;
import CatCloud.Server.TCPRequest;
import CatCloud.Server.TCPResponce;
import CatCloud.Server.Handler.BaseServerHandler;
import CatCloud.Server.Handler.MessageBean;

/**
 * 服务器端的处理器
 * @author Administrator
 *
 */
public class ServerHelloHandler extends BaseServerHandler{



	@Override
	public boolean canHandlerMessage(MessageBean bean) {
		
		return bean.getSendType().equals(Config.SEND_TYPE_TOSERVER)&&bean.getMsgType().equals("hello");
	}

	@Override
	public void handlerMessage(TCPRequest request, TCPResponce responce) {
		
		
		//发送一条默认的回应消息
		responce.defaultResponce();
		
	}

	

}
