package com.xktj.yangml.machine_10003;

import java.io.InputStream;
import java.io.ObjectOutputStream;
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

            ObjectOutputStream  out  = new ObjectOutputStream(socket.getOutputStream());

//            out.writeObject();



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