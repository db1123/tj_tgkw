package fun.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import fun.server.model.TUser;
import fun.server.model.TUserExample;
import fun.server.service.TUserService;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * WebSocket服务端代码，包含接收消息，推送消息等接口
 */
@Component
@RestController
@ServerEndpoint(value = "/message/{name}")
public class MessageServer {

    // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static AtomicInteger online = new AtomicInteger();

    // concurrent包的线程安全Set，用来存放每个客户端对应的MessageServer对象。
    private static Map<String, Session> sessionPools = new HashMap<>();

    // 用户服务类
    private static TUserService staticTUserService;
    @Autowired
    private TUserService userService;
    @PostConstruct
    private void init () {
        staticTUserService = userService;
    }

    /**
     * 发送消息方法
     * @param session 客户端与socket建立的会话
     * @param message 消息
     * @throws IOException
     */
    public void sendMessage(Session session, String message) throws IOException{
        if(session != null){
            session.getBasicRemote().sendText(message);
        }
    }

    /**
     * 连接建立成功调用
     * @param session 客户端与socket建立的会话
     * @param userName 客户端的userName
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "name") String userName){
        sessionPools.put(userName, session);
        addOnlineCount();
        //System.out.println(userName + "进入系统！当前人数为" + online);
        try {
            // 激活数据库用户活跃状态
            TUser user = getUser(userName);
            user.setfActive(1);
            staticTUserService.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接时调用
     * @param userName 关闭连接的客户端的姓名
     */
    @OnClose
    public void onClose(@PathParam(value = "name") String userName){
        sessionPools.remove(userName);
        subOnlineCount();
        //System.out.println(userName + "断开系统连接！当前人数为" + online);
        try {
            // 关闭数据库用户活跃状态
            TUser user = getUser(userName);
            user.setfActive(0);
            staticTUserService.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发生错误时候
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable){
        System.out.println(throwable.getMessage());
        throwable.printStackTrace();
    }

    /**
     * 收到客户端消息时触发（群发）
     * @param message
     * @throws IOException
     */
    @OnMessage
    public void onMessage(String message) throws IOException{
        for (Session session: sessionPools.values()) {
            try {
                sendMessage(session, message);
            } catch(Exception e){
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 给指定用户发送消息
     * @param userName 用户名
     * @param message 消息
     * @throws IOException
     */
    public void sendInfo(String userName, String message){
        Session session = sessionPools.get(userName);
        try {
            sendMessage(session, message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 通过线程安全的方式操作加减
     */
    public static void addOnlineCount(){
        online.incrementAndGet();
    }
    public static void subOnlineCount() {
        online.decrementAndGet();
    }

    /**
     * 根据用户名获取用户模型
     * @param username 用户名
     * @return
     */
    private TUser getUser(String username) {
        TUser user = null;
        TUserExample userExample = new TUserExample();
        userExample.or().andFLoginEqualTo(username);
        List<TUser> userList = staticTUserService.findByExample(userExample);
        if (userList.size() > 0) {
            user = userList.get(0);
        }
        return user;
    }
}