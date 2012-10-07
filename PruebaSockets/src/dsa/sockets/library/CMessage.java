package dsa.sockets.library;

import java.io.Serializable;

public class CMessage implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 20121007L;
	
	MessageType code;
	CPlayer player;
	public CMessage(int code, CPlayer player) {
		super();
		this.code = MessageType.withCode(code);
		this.player = player;
	}
	public MessageType getCode() {
		return code;
	}
	public CPlayer getPlayer() {
		return player;
	}
	@Override
	public String toString() {
		
		return "message.code: " + code + " " + player.toString();
	}
	
}
