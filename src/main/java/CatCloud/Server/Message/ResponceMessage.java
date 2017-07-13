package CatCloud.Server.Message;

import CatCloud.Client.Request.BaseMessage;
import CatCloud.Client.Request.Config;

public class ResponceMessage extends BaseMessage {

	
	public ResponceMessage(int id) {
		this.id=id;
		this.type=Config.TYPE_RESPONCE;
	}

}
