package com.tickets.socket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


/**
 * @author zhengkai.blog.csdn.net
 */
@ServerEndpoint("/imserver/{vaId}")
@Component
public class RealTimePeopleSocketServer {

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;

    /**
     * 保存活动的Id
     */
    public static List<String> vaIds = new ArrayList<>();
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    public static ConcurrentHashMap<String, RealTimePeopleSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收userId
     */
    private String userId = "";

    /**
     * 活动id
     */
    private String vaId;

    /**
     * 1 实时入口人数信息
     * 2 各个入口人数信息
     * 3 其它信息
     */
    private Integer function;

    /**
     * 连接建立成功调用的方法
     */
    public void onOpen(Session session, @PathParam("vaId") String vaId, @PathParam("function") Integer function) {
        this.session = session;
        this.vaId = vaId;
        this.function = function;
        vaIds.add(vaId);
        if (webSocketMap.containsKey(vaId)) {
            webSocketMap.remove(vaId);
            webSocketMap.put(vaId, this);
        } else {
            webSocketMap.put(vaId, this);
        }
    }
//    @OnOpen
//    public void onOpen(Session session, @PathParam("userId") String userId) throws IOException {
//        this.session = session;
//        this.userId = userId;
//        if (webSocketMap.containsKey(userId)) {
//            webSocketMap.remove(userId);
//            webSocketMap.put(userId, this);
//            //加入set中
//        } else {
//            webSocketMap.put(userId, this);
//            //加入set中
//            addOnlineCount();
//            //在线数加1
//        }
//        System.out.println("用户连接:" + userId + ",当前在线人数为:" + getOnlineCount());
//        try {
//            sendMessage("连接成功");
//        } catch (IOException e) {
//            System.out.println("用户:" + userId + ",网络异常!!!!!!");
//        }
//    }

    @OnClose
    public void onClose(){
        if (webSocketMap.containsKey(vaId)) {
            webSocketMap.remove(vaId);
            subOnlineCount();
        }
    }

    /**
     * 连接关闭调用的方法
     */
//    @OnClose
//    public void onClose() {
//        if (webSocketMap.containsKey(userId)) {
//            webSocketMap.remove(userId);
//            //从set中删除
//            subOnlineCount();
//        }
//        System.out.println("用户退出:" + userId + ",当前在线人数为:" + getOnlineCount());
//    }


    @OnMessage
    public void  onMessage(String message, Session session){}

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
//    @OnMessage
//    public void onMessage(String message, Session session) {
//        System.out.println("用户消息:" + userId + ",报文:" + message);

//        System.out.println(message);

        //可以群发消息
        //消息保存到数据库、redis
//        if(StringUtils.isNotBlank(message)){
//            try {
//                //解析发送的报文
//                JSONObject jsonObject = JSON.parseObject(message);
//                //追加发送人(防止串改)
//                jsonObject.put("fromUserId",this.userId);
//                String toUserId=jsonObject.getString("toUserId");
//                //传送给对应toUserId用户的websocket
//                if(StringUtils.isNotBlank(toUserId)&&webSocketMap.containsKey(toUserId)){
//                    webSocketMap.get(toUserId).sendMessage(jsonObject.toJSONString());
//                }else{
//                    log.error("请求的userId:"+toUserId+"不在该服务器上");
//                    //否则不在这个服务器上，发送到mysql或者redis
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("用户错误:" + this.userId + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }




    /**
     * 发送自定义消息
     */
//    public static void sendInfo(String message, @PathParam("userId") String userId) throws IOException {
//        if (!StringUtils.isEmpty(userId) && webSocketMap.containsKey(userId)) {
//            webSocketMap.get(userId).sendMessage(message);
//        } else {
//            System.out.println("用户" + userId + ",不在线！");
//        }
//    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        RealTimePeopleSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        RealTimePeopleSocketServer.onlineCount--;
    }

    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //addEndpoint表示添加了一个/socket端点，客户端就可以通过这个端点来进行连接。
        //withSockJS()的作用是开启SockJS支持(指定使用SockJS协议)
        registry.addEndpoint("/socket").setAllowedOrigins("*").withSockJS();
    }

}
