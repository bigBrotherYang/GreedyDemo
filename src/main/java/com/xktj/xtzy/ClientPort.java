package com.xktj.xtzy;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

import org.junit.Test;

public class ClientPort {
    public static void main(String[] args) {
        new ClientPort().startClient();
    }

    public void startClient() {
        Scanner sc = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 9999);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            MsgBen msgBen = new MsgBen();
//			msgBen.setOthId(1001);
//			ThreadDemo.userId = 1001;
            msgBen.setUserId(1001);
            while (true) {
                System.out.println("请输入要传递的消息");
                String msg = sc.nextLine();
                if (msg.length() > 1)
                    msgBen.setMsg(msg);
                output.writeObject(msgBen);
                output.flush();

                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                MsgBen o = (MsgBen) in.readObject();
                System.out.println("||" + o);
            }

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }

}
