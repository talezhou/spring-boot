package com.iinaq.springboot.point;

import com.iinaq.springboot.common.CacheKeyGenerator;
import com.iinaq.springboot.utils.WebSocketUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @ClassName ChatRoomServerEndPoint
 * @Description TODO
 * @Author zhouzhongshan
 * @Date 2019/7/1  14:19
 **/
@ServerEndpoint("/chat-room/{username}")
public class ChatRoomServerEndPoint {
    private static final Logger log= LoggerFactory.getLogger(ChatRoomServerEndPoint.class);

    @OnOpen
    public void openSession(@PathParam("username") String username,Session session){
        WebSocketUtils.LIVING_SESSION_CACHE.put(username,session);
        String message = "欢迎"+username+"来到聊天室!";
        log.info(message);
        WebSocketUtils.sendMessageAll(message);
    }

    @OnMessage
    public void onMessage(@PathParam("username") String username,String message){
        log.info(message);
        WebSocketUtils.sendMessageAll("用户"+username+":"+message);
    }

    @OnClose
    public void onClose(@PathParam("username") String username,Session session){
        WebSocketUtils.LIVING_SESSION_CACHE.remove(username);
        WebSocketUtils.sendMessageAll("用户"+username+"已经离开聊天室");
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Session session,Throwable throwable){
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throwable.printStackTrace();
    }
}
