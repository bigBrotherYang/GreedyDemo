package com.xktj.yangml.machine_10003;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientEmo  {
    public static void main(String[] args) {

        try{
            Socket socket = new Socket("localhost",9999);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class Em1 extends Thread{
    private Socket socket;
    Em1(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            Scanner sc = new Scanner(System.in);
            PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
            System.out.println("请输入要传递的值");
             pw.println(sc.next());

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}

class Em extends Thread{
    private Socket socket;
    Em(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
           Scanner sc = new Scanner( socket.getInputStream());
           while (sc.hasNext()){
               System.out.println(sc.nextLine());
           }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}