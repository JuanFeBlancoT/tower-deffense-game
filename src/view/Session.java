package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Session extends Thread{

	private BufferedReader bfr;
	private BufferedWriter bfw;
	private Socket connection;
	private IObserver obs;
	private MainServer mainServ;
	int idSener;
	
	public Session(Socket ss, IObserver obs, int id, MainServer ms) {
		mainServ = ms;
		connection = ss;
		this.obs = obs;
		idSener = id;
	}
	
	public void run() {
		startSession();
	}
	
	public void startSession() {
		
		try {
			InputStream is = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			bfr = new BufferedReader(isr);
			
			OutputStream os = connection.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			bfw = new BufferedWriter(osw);
			
			while(true) {
				String message = bfr.readLine();
				//System.out.println(message);
				String[] coordinates = message.split(";");
				int px = Integer.parseInt(coordinates[0])/2;
				int py = (Integer.parseInt(coordinates[1])/2);
				//System.out.println("****"+px+","+py);
				mainServ.onMessage(px, py, idSener);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String msg) {
		new Thread(
				()->{
					try {
						bfw.write(msg);
						bfw.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}).start();
	}
	
		
}
