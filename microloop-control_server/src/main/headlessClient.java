package main;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import org.java_websocket.client.WebSocketClient;

public class headlessClient {

	public static void main(String[] args) {
		Scanner readLine = new Scanner(System.in);
		try {
			WebSocketClient client = new wsClient(new URI("ws://192.168.31.243:8887"));
			client.connect();
			while(true) {
				System.out.print(">");
				client.send(readLine.nextLine());
			}
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
