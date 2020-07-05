package com.xktj.yangml.machine_10000;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerSocketDemo6 {

    public static final List<Client> CLIENTS = new ArrayList<Client>();
    public ServerSocketDemo6() {
        //局部变量没有默认值,必须先初始化,再使用
        // TODO Auto-generated method stub
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(8080);
            System.out.println("服务器8080端口正常开启");
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException("服务器端口被占用");
        }

        System.out.println("服务器等待客户端连接");
        try {
            while(true){
                socket = serverSocket.accept();
                System.out.println("有客户端连接成功");
                System.out.println(socket.getLocalAddress()+"    "+socket.getLocalPort()+"  "+
                        socket.getInetAddress()+"  "+socket.getPort());
                Client client = new Client(socket);
                CLIENTS.add(client);
                System.out.println(CLIENTS);
                client.start();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ServerSocketDemo6();

    }
}
