package main;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;

public class headlessClient {

	public static void main(String[] args) {
		try {
			WebSocketClient client = new wsClient(new URI("ws://192.168.31.243:8887"));
			client.connect();
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
