package com.xktj.yangml.machine_10003;



import com.xktj.yangml.machine_10002.ChatSocket;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ChatManager {

    Map<String,StartTherad> map = new HashMap<>();
    private static final ChatManager cm = new ChatManager();
    private ChatManager(){};
    public static ChatManager getChatWanager(){
        return cm;
    }
    //添加聊天人
    public void AddChatPeople(StartTherad cs) {
        map.put(cs.getUserId(),cs);
    }


    //群发消息
    public  void Send(StartTherad cs, String str) {

        if (map.containsKey(cs.getOtheId())){
            StartTherad therad = map.get(cs.getOtheId());
            therad.Out(str);
        }

//        for (int i = 0; i < vector.size(); i++) {
//            ChatSocket chatsocket=(ChatSocket)vector.get(i);
//            if(!cs.equals(chatsocket))
//            {
//                chatsocket.Out(str);
//            }
//        }
    }

}
