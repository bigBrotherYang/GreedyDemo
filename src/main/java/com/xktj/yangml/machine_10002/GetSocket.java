package com.xktj.yangml.machine_10002;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GetSocket extends Thread {
    @Override
    public void run() {

        try {
            //创建绑定到特定端口的服务器套接字  1-65535
            ServerSocket serversocket = new ServerSocket(62224);
            while(true) {
                //建立连接，获取socket对象
                Socket socket=serversocket.accept();
                System.out.println(socket.getInetAddress().getHostName());
                //消息提示框
                JOptionPane.showMessageDialog(null, "有客户端连接到了本机62224端口哦");
                //与客户端通信
                ChatSocket cs=new ChatSocket(socket);
                cs.start();
                //添加socket到队列
                ChatManager.GetChatManager().AddChatPeople(cs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
