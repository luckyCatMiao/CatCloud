package CatCloud.Server.Handler;

import CatCloud.Client.Message.Config;
import CatCloud.Server.TCPRequest;
import CatCloud.Server.TCPResponce;
import CatCloud.Server.Message.Responce.GetClientIDResponce;

public class GetClientIDHandler extends BaseServerHandler{

	@Override
	public boolean canHandlerMessage(MessageBean bean) {
		// TODO Auto-generated method stub
		return bean.getSendType().equals(Config.SEND_TYPE_TOSERVER)&&bean.getMsgType().equals(Config.TYPE_GET_CLIENT_ID);
	}

	@Override
	public void handlerMessage(TCPRequest request, TCPResponce responce) {
	
		
		responce.sendResponce(new GetClientIDResponce(request.getSocketHandler().getClientID()));
		
	}

}
