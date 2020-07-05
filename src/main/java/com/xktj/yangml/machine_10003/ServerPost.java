package com.xktj.yangml.machine_10003;

import com.xktj.xtzy.ServerPort;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerPost {

    public static void main(String[] args) {
        ServerPort sp = new ServerPort();
        sp.startServer();
    }
    public void startServer(){
        try {
            ServerSocket ss = new ServerSocket(9999);
            while (true){
                Socket socket = ss.accept();
                StartTherad chatManager = new StartTherad(socket);
                new Thread(chatManager).start();
                ChatManager.getChatWanager().AddChatPeople(chatManager);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }



}
