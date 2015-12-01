package com.sclovel.chat;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
 
@ServerEndpoint("/websocket")
public class WebSocketTest {
 
  @OnMessage
  public void onMessage(String message, Session session)
    throws IOException, InterruptedException {
  // Sample
    // Print the client message for testing purposes
    System.out.println(" ’µΩ: " + message +" "+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
   
    // Send the first message to the client
    session.getBasicRemote().sendText("This is the first server message");
   
    // Send 3 messages to the client every 5 seconds
    int sentMessages = 0;
    while(sentMessages < 100){
      Thread.sleep(2000);
      session.getBasicRemote().
        sendText("This is an intermediate server message. Count: "
          + sentMessages);
      sentMessages++;
    }
   
    // Send a final message to the client
    session.getBasicRemote().sendText("This is the last server message");
  }
   
  @OnOpen
  public void onOpen(Session session) {
    System.out.println(session.getId()+":--->Client connected~!");
  }
 
  @OnClose
  public void onClose(Session session) {
    System.out.println(session.getId()+ ":----->Connection closed");
  }
}