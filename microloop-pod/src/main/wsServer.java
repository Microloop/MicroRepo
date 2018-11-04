package main;

import java.net.InetSocketAddress;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class wsServer extends WebSocketServer{

	public wsServer(InetSocketAddress address) {
		super(address);
	}
	
	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		System.out.println("Client (" + conn.getRemoteSocketAddress() + ") disconnected from pod with status code " + code + ". Reason: " + reason);
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		System.err.println("an error occured on connection " + conn.getRemoteSocketAddress()  + ":" + ex);
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		// TODO Auto-generated method stub
		
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
