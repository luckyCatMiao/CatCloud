package CatCloud.Server.Handler.RoomHandler;

import org.json.JSONException;

import CatCloud.Client.Message.Config;
import CatCloud.Server.TCPRequest;
import CatCloud.Server.TCPResponce;
import CatCloud.Server.Handler.BaseServerHandler;
import CatCloud.Server.Handler.MessageBean;

public class CreateRoomHandler extends BaseServerHandler{

	@Override
	public boolean canHandlerMessage(MessageBean bean) {
		// TODO Auto-generated method stub
		return bean.getSendType().equals(Config.SEND_TYPE_TOSERVER)&&bean.getMsgType().equals(Config.TYPE_CREATE_ROOM);
	}

	@Override
	public void handlerMessage(TCPRequest request, TCPResponce responce) {
		
		
		String roomName = null;
		try {
			roomName = request.getRequestData().getString(Config.KEY_ROOMNAME);
			request.getSocketHandler().getServer().createRoom(roomName);
			responce.defaultResponce();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

}
