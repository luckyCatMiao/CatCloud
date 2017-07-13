package Main;

import CatCloud.Client.Request.BaseMessage;

public class HelloMessage extends BaseMessage{

	public HelloMessage() {
		setType("hello");
	}
}
