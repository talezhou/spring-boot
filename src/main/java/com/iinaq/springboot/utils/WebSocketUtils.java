package com.iinaq.springboot.utils;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class WebSocketUtils {
    public static final Map<String,Session> LIVING_SESSION_CACHE =  new ConcurrentHashMap<>();

    public static void sendMessageAll(String message){
        LIVING_SESSION_CACHE.forEach((sessionId,session) -> sendMessage(session,message));
    }

    public static void sendMessage(Session session,String message){
        if (session == null){
            return;
        }
        final RemoteEndpoint.Basic remote = session.getBasicRemote();
        if (remote == null){
            return;
        }
        try {
            remote.sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
