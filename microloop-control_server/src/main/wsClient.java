package main;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class wsClient extends WebSocketClient{

	public wsClient(URI serverURI) {
		super(serverURI);
	}
	
	@Override
	public void onClose(int code, String reason, boolean remote) {
		System.out.println("Disconnected from pod with exit code " + code + ". Reason: " + reason);
		if(code == -1) {
			System.out.println("The pod server may be down. Try restarting the pod or make sure the server address is correct");
		}
	}

	@Override
	public void onError(Exception ex) {
		System.err.println("an error occurred:" + ex);
	}

	@Override
	public void onMessage(String message) {
		System.out.println(message);
		
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		// TODO Auto-generated method stub
		System.out.println("Connected to pod at address " + this.getRemoteSocketAddress());
		System.out.println("\n\n");
	}
}
