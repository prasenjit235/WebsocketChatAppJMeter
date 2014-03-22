package com.prasenjit.examples.test.clients;

import java.net.URI;

import org.eclipse.jetty.websocket.WebSocketClient;

public class WebsocketClient {

	private ChatWebsocket chatWebsocket;
	
	public static void main(String[] args) {
		String uri="ws://localhost:8080/WebSocketChatApp/samplewebsocketchat";
		WebSocketClient webSocketClient=null;
		try {
			webSocketClient=new WebSocketClient();
			ChatWebsocket websocket=new ChatWebsocket();
			URI serverURI=new URI(uri);
			webSocketClient.open(serverURI, websocket);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public void start(String URI,String user) {
		WebSocketClient webSocketClient=null;
		try {
			webSocketClient=new WebSocketClient();
			ChatWebsocket websocket=new ChatWebsocket(user);
			URI serverURI=new URI(URI);
			this.chatWebsocket=websocket;
			webSocketClient.open(serverURI, websocket);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public void sendHandShakeReq() {
		this.chatWebsocket.sendConnectionInit();
	}
	
	public void sendFrndToFrndMsg(String fromFriend,String toFriend) {
		this.chatWebsocket.sendMsgToFriend(fromFriend, toFriend);
	}
}
