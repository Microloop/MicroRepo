package main;

import java.net.InetSocketAddress;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class WSServer extends WebSocketServer{

	public WSServer(InetSocketAddress address) {
		super(address);
	}
	
	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		System.out.println("Client (" + conn.getRemoteSocketAddress() + ") disconnected from pod with exit code " + code + ". Reason: " + reason);
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		System.err.println("an error occured on connection " + conn.getRemoteSocketAddress()  + ":" + ex);
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		if(message.startsWith("i2cstart")) {
			conn.send("Starting I2C connection to arduino...");
			System.out.println("I2C connection to arduino requested for initialization by client(" + conn.getRemoteSocketAddress() + "). Starting connection...");
		}
		else {
			conn.send("Error: Invalid command syntax");
		}
	}

	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		conn.send("Connected to MicroLoop Pod\nSoftware version 0.0.1");
		System.out.println("Client connected to pod with address " + conn.getRemoteSocketAddress());
	}

	@Override
	public void onStart() {
		System.out.println("Pod server started on address " + this.getAddress());
	}

}
