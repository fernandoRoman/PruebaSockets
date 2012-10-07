package dsa.sockets.library;

public enum MessageType {
	REGISTER (0), 
	VALIDATION (1), 
	DISCONNECT (2);
	
	private final int code;
	
	/**
	 * @param code
	 */
	private MessageType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	public static MessageType withCode(int code)
	{
		for(MessageType m : MessageType.values())
		{
			if (code == m.getCode()) return m;
		}
		return null;
	}
}
