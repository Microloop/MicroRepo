package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeadlessClient {
	
	private static final Logger logger = LoggerFactory.getLogger(HeadlessClient.class);

	public static void main(String[] args) {
		
		File logConfig = new File("log4j.properties");
		
		String log4JPropertyFile = logConfig.getAbsolutePath();
		Properties p = new Properties();

		try {
			System.out.println("Loading log4j.properties file");
			p.load(new FileInputStream(log4JPropertyFile));
			PropertyConfigurator.configure(p);
			logger.info("Successfully loaded log4j.properties file");
		} catch (IOException e) {
			System.out.println("ERROR: Could not set " + logConfig.getAbsolutePath() + " as properties file");
		}
		
		Scanner readLine = new Scanner(System.in);
		try {
			WebSocketClient client = new WSClient(new URI("ws://192.168.31.243:8887"));
			client.connect();
			while(true) {
				try {
					client.send(readLine.nextLine());
				} catch (WebsocketNotConnectedException e) {
					System.out.println("Error: not connected to pod. Attempting to reconnect...");
					client = new WSClient(new URI("ws://192.168.31.243:8887"));
					client.connect();
				}
			}
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
