package com.xktj.xtzy;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.junit.Test;

public class ClientPort {
	@Test
	public void startClient() {
		
		try {
			Socket socket = new Socket("localhost",9999);
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			output.writeObject(new MsgBen());
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
