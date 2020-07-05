package com.xktj.xtzy;

import org.junit.Test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientPort1 {
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        new ClientPort1().startClient();
    }

    public void startClient() {

        try {
            Socket socket = new Socket("localhost", 9999);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            MsgBen msgBen = new MsgBen();
//            ThreadDemo.userId = 1001;
//            ThreadDemo.otheId = 1002;
            msgBen.setOthId(1001);
            msgBen.setUserId(1002);
            while (true) {

                System.out.println("请输入要传递的消息");
                String msg = sc.nextLine();
                if (msg.length() > 1)
                    msgBen.setMsg(msg);
                output.writeObject(msgBen);
                output.flush();

                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                MsgBen o = (MsgBen) in.readObject();
                System.out.println("--" + o);
            }

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }

}
