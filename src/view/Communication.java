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
			com.start();
		}
		
		return com;
		
	}
	
	public void run() {
		startServer();
	}
	
	public void startServer() {
		try {
			ServerSocket ss = new ServerSocket(8000);
			while(session1 == null || session2 == null) {
				if(session1 == null) {
					Socket connection = ss.accept();
					session1 = new Session(connection, observer);
					session1.start();
				}else {
					Socket connection = ss.accept();
					session2 = new Session(connection, observer);	
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
