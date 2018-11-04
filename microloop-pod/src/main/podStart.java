package main;

import java.net.InetSocketAddress;

import org.java_websocket.server.WebSocketServer;

public class podStart {

	public static void main(String[] args) {
		String host = "0.0.0.0";
		int port = 8887;
		
		WebSocketServer server = new wsServer(new InetSocketAddress(host, port));
		server.run();
	}

}
