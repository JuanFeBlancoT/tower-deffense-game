package view;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Communication extends Thread{
	
	private IObserver observer;
	private static Communication com;
	private Session session1;
	private Session session2;
	private MainServer mainServer;
	
	private  Communication() {
		
	}
	
	public static Communication ComgetInstance() {
		 	
		if(com == null) {
			com = new Communication();
		}
		return com;
	}
	
	public void run() {
		startServer();
	}
	
	public void startServer() {
		try {
			ServerSocket ss = new ServerSocket(8000);
			System.out.println("WAITING CONNECTIONS...");
			
			
			while(session1 == null || session2 == null) {
				System.out.println("CONNECTING...");
				
				Socket connection = ss.accept();
				System.out.println("PLAYER CONNECTED...");
				if(session1 == null) {
					session1 = new Session(connection, observer, 1, mainServer);
					this.mainServer.createPlayer();
					session1.start();
					
				}else {
					session2 = new Session(connection, observer, 2, mainServer);	
					this.mainServer.createPlayer();
					this.mainServer.setScreen(2);
					session2.start();					
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public IObserver getObserver() {
		return observer;
	}

	public void setObserver(IObserver observer) {
		this.observer = observer;
	}

	public MainServer getMainServer() {
		return mainServer;
	}

	public void setMainServer(MainServer mainServer) {
		this.mainServer = mainServer;
	}
	
	
}
