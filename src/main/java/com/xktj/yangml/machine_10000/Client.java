package com.xktj.yangml.machine_10000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread  {

    private String ip;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter out;

    @Override
    public String toString() {
        return "Client [ip=" + ip + "]";
    }

    public String getIP() {
        return ip;
    }

    public PrintWriter getOut() {
        return out;
    }

    public Client(Socket socket) {
        this.socket = socket;
        ip = socket.getInetAddress().getHostAddress();
        System.out.println(ip);
        try {
            reader = new BufferedReader(new InputStreamReader(
                    socket.getInputStream(),"UTF-8"));

            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    // 运行在服务器端,接受客户端发过来的信息,
    @Override
    public void run() {
        super.run();
        try {
            String str = null;
            // 服务器端,循环接受数据
            while ((str = reader.readLine()) != null) {

                String[] strs = str.split(":");
                //socket.getInetAddress().getHostAddress()  谁发的信息
                //strs[0]  接受信息目标
                //strs[1]  信息内容
                System.out.println("接受到来自  "+socket.getInetAddress().getHostAddress()+"  "+strs[1]);
                String ip = strs[0];
                if (str.equals("exit"))
                    break;
                else if ("all".equalsIgnoreCase("ip")) {
                    for (Client client : ServerSocketDemo6.CLIENTS) {
                        client.getOut().println(strs[1]);
                    }
                } else {
                    for (Client client : ServerSocketDemo6.CLIENTS) {
                        if (client.getIP().equals(ip)) {
                            client.getOut().println(strs[1]);
                            break;
                        }
                    }
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            if (socket != null)
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }

    }

}
