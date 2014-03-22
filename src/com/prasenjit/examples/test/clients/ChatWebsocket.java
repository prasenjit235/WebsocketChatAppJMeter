package com.prasenjit.examples.test.clients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocket.OnBinaryMessage;
import org.eclipse.jetty.websocket.WebSocket.OnTextMessage;

public class ChatWebsocket implements WebSocket,OnBinaryMessage,OnTextMessage{

	private Connection connection;
	private BlockingQueue<String> blockingQueue;
	private ObjectMapper requestMapper;
	private String connectorId;
	private String user;
	
	public ChatWebsocket(){
		System.out.println("ChatWebsocket.ChatWebsocket()");
		requestMapper=new ObjectMapper();
	}
	
	
	public ChatWebsocket(String user) {
		super();
		System.out.println("ChatWebsocket.ChatWebsocket()");
		this.user = user;
		requestMapper=new ObjectMapper();
	}


	@Override
	public void onClose(int arg0, String arg1) {
		System.out.println("ChatWebsocket.onClose()");
	}

	@Override
	public void onOpen(Connection arg0) {
		System.out.println("ChatWebsocket.onOpen()");
		this.connection=arg0;
	}

	@Override
	public void onMessage(byte[] arg0, int arg1, int arg2) {
		System.out.println("ChatWebsocket.onMessage()");
	}

	@Override
	public void onMessage(String arg0) {
		System.out.println("ChatWebsocket.onMessage()"+arg0);
		try {
			WebSocketRes connectionInitRes=requestMapper.readValue(arg0, WebSocketRes.class);
			if ("ConnectInit".equals(connectionInitRes.getResponseId())) {
				this.connectorId=connectionInitRes.getConnectorId();
				this.sendConnectionInit();
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*private void doLogin(){
		System.out.println("ChatWebsocket.doLogin()");
		String userName="login2";
		String pwd="123456";
		String input="{\"username\":"+userName+",\"password\":"+pwd+",\"connectorId\":"+this.connectorId+"}";
		try{
		HttpClient httpClient=new DefaultHttpClient();
		HttpPost httpPost=new HttpPost("http://localhost:8080/WebSocketChatApp/rest/login");
		StringEntity se = new StringEntity(input);
		se.setContentType("application/json");
		httpPost.setEntity(se);
		HttpResponse response = httpClient.execute(httpPost);
		
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }
		}catch (Exception e) {
			e.printStackTrace();
		}
		try{
		 ClientConfig config = new DefaultClientConfig();
		  Client client = Client.create(config);
		  client.addFilter(new LoggingFilter());
		  WebResource service = client.resource("http://localhost:8080/WebSocketChatApp");
		  System.out.println(service.path("rest").path("/login").accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, input));//.accept(MediaType.TEXT_PLAIN)
		}catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public void sendConnectionInit(){
		WebsocketRequest request=new WebsocketRequest();
		request.setConnectorId(connectorId);
		request.setUserId(this.user);
		request.setRequestId("HandShakeReq");
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			String res=objectMapper.writeValueAsString(request);
			connection.sendMessage(res);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendMsg(String Msg) {
		try {
			this.connection.sendMessage(Msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//FriendToFriend
	public void sendMsgToFriend(String fromFriend,String toFriend) {
		WebsocketRequest request=new WebsocketRequest();
		request.setConnectorId(connectorId);
		request.setUserId(fromFriend);
		request.setRequestId("FriendToFriend");
		ArrayList<String> friendList=new ArrayList<String>();
		friendList.add(toFriend);
		request.setFriendList(friendList);
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			String res=objectMapper.writeValueAsString(request);
			connection.sendMessage(res);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
