package com.xktj.yangml.machine_10003;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class  StartTherad implements Runnable{
    private String userId;
    private String otheId;
    private Socket socket ;
    StartTherad(Socket socket){
        this.socket = socket;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOtheId() {
        return otheId;
    }

    public void setOtheId(String otheId) {
        this.otheId = otheId;
    }

    @Override
    public void run() {
        Out("success\n");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));

            String line=null;
            while((line=br.readLine())!=null)
            {
                System.out.println(line);
                ChatManager.getChatWanager().Send(this, line);
            }
            br.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /*
     *向客户端输出信息
     */
    public void Out(String str) {
        try {
            socket.getOutputStream().write(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
