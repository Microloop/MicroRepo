package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PodStart {
	
	private static final Logger logger = LoggerFactory.getLogger(PodStart.class);

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
		
		String host = "0.0.0.0";
		int port = 8887;
		
		WebSocketServer server = new WSServer(new InetSocketAddress(host, port));
		server.run();
	}
}
