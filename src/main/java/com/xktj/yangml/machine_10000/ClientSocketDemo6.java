package com.xktj.yangml.machine_10000;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocketDemo6 {
    public ClientSocketDemo6() {
        Socket socket = null;


        try {
            //此处应写目标客户端的ip
            socket = new Socket("127.0.0.1", 8080);
            System.out.println(socket.getLocalAddress()+"    "+socket.getLocalPort()+"  "+
                    socket.getInetAddress()+"  "+socket.getPort());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "请检查ip和端口是否正确,无法连接服务器");
            throw new RuntimeException("请检查ip和端口是否正确,无法连接服务器");
        }
        System.out.println("您好,如果退出,输入exit,如果发送信息,请输入ip:message");
        new SendInfoThread(socket).start();
        new GetInfoThread(socket).start();



    }
    class GetInfoThread extends Thread{
        private Socket socket;
        private BufferedReader reader;
        public GetInfoThread(Socket socket){
            this.socket = socket;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new RuntimeException("请检查ip和端口是否正确,无法连接服务器");
            }
        }
        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            String str = null;
            try {
                while ((str = reader.readLine()) != null){
                    System.out.println("收到来自服务器     "+socket.getInetAddress().getHostAddress()+"  "+str);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    class SendInfoThread extends Thread{
        private Scanner scanner = new Scanner(System.in);
        private PrintWriter out;
        private Socket socket;
        public SendInfoThread(Socket socket){
            this.socket = socket;
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new RuntimeException("请检查ip和端口是否正确,无法连接服务器");
            }
        }
        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();

            try {
                String str = null;
                while(true){
                    str = scanner.nextLine();
                    out.println(str);
                    if(str.equals("exit"))
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "服务器出异常,请联系管理员");
            } finally{
                if(out != null)
                    out.close();
                if(socket != null)
                    try {
                        socket.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
            }

        }
    }


    public static void main(String[] args) {
        new ClientSocketDemo6();
    }
}
