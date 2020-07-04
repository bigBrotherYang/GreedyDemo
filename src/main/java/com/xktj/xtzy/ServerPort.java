package com.xktj.xtzy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ServerPort {
	/**
	 * 存储连接用户连接的socket
	 */
	public Map<Integer, Socket> map = new HashMap<Integer, Socket>();
	@Test
	public void startServer() {
		try {
			ServerSocket ss = new ServerSocket(9999);
			System.out.println("等待客户端进行连接，，，，，，");
			Socket socket = ss.accept();
//			Socket so = new Socket
			System.out.println("连接成功");
			ObjectInputStream in1 = new ObjectInputStream(socket.getInputStream());
			MsgBen readObject1 =(MsgBen)in1.readObject();
			
			this.a(socket);
			
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void a(Socket socket) {
		try {
			ObjectInputStream in2 = new ObjectInputStream(socket.getInputStream());
			MsgBen readObject2 =(MsgBen)in2.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
}
