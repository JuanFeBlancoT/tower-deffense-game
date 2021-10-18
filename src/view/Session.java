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
	
	public Session(Socket ss, IObserver obs) {
		connection = ss;
		this.obs = obs;
	}
	
	public void run() {
		
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
