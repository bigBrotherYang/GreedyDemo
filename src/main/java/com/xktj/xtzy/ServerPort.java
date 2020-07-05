package com.xktj.xtzy;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class ServerPort {

    /**
     * 存储连接用户连接的socket
     */
    public Map<Integer, Socket> map = new HashMap<Integer, Socket>();


    public static void main(String[] args) {
        new ServerPort().startServer();
    }

    public void startServer() {
        try {
            ServerSocket ss = new ServerSocket(9999);
            System.out.println("等待客户端进行连接，，，，，，");
            while (true) {
                Socket socket = ss.accept();
                System.out.println("连接成功");
                ObjectInputStream in1 = new ObjectInputStream(socket.getInputStream());
                MsgBen mb = (MsgBen) in1.readObject();
                map.put(mb.getUserId(), socket);
                new Thread(new ThreadDemo(map, mb.getUserId(), mb.getOthId())).start();
            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void a(Socket socket) {
        try {
            ObjectInputStream in2 = new ObjectInputStream(socket.getInputStream());
            MsgBen mb = (MsgBen) in2.readObject();
            System.out.println(mb);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

}

class ThreadDemo implements Runnable {

    private int userId;
    private int otheId;

    MsgBen o = new MsgBen();
    public Map<Integer, Socket> map;

    public ThreadDemo(Map<Integer, Socket> map, int... id) {
        this.map = map;
        this.userId = id[1];
        this.otheId = id[0];
    }

    @Override
    public void run() {
        try {
            System.out.println("ThreadDemo.otheId = " + otheId);
            if (otheId == 0) {
                return;
            }


            if (map.containsKey(otheId)) {
                Socket user1 = map.get(otheId);
                Socket user2 = map.get(userId);
                /**
                 * 被连接
                 */

                    ObjectOutputStream outputStream = new ObjectOutputStream(user1.getOutputStream());
                    outputStream.writeObject(o);
                    outputStream.flush();

                    ObjectInputStream inputStream = new ObjectInputStream(user1.getInputStream());
                    o = (MsgBen) inputStream.readObject();


//                    ObjectOutputStream outputStream1 = new ObjectOutputStream(user2.getOutputStream());
//                    outputStream1.writeObject(o);
//                    outputStream1.flush();
//
//                    ObjectInputStream inputStream1 = new ObjectInputStream(user2.getInputStream());
//                    o = (MsgBen) inputStream1.readObject();

            } else {
                System.out.println("当前用户没上线!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public void
}