package co.domi.clientapptowerdeffense;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Communication extends Thread{

    private static Communication com;
    private MsgListener observer;
    private Socket socket;
    private BufferedWriter bfw;
    private BufferedReader bfr;

    private String line;

    private  Communication() {

    }

    public static Communication ComgetInstance() {
        //verify that theres only one instance of communication
        if(com == null) {
            com = new Communication();
        }
        return com;
    }

    public void run() {
       startClient();
    }

    private void startClient() {
        try {
            socket = new Socket("10.0.2.2", 8000);

            //listener
            InputStream is = socket.getInputStream();
            InputStreamReader isr= new InputStreamReader(is);
            bfr = new BufferedReader(isr);

            //writter
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osr = new OutputStreamWriter(os);
            bfw = new BufferedWriter(osr);

            //keep listening
            while (true){
                line = bfr.readLine();

                observer.msgReceived(line);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void sendMessage(String msg){
        new Thread(
                ()->{
                    try {
                        bfw.write(msg+"\n");
                        bfw.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
        ).start();
    }

    public MsgListener getObserver() {
        return observer;
    }

    public void setObserver(MsgListener observer) {
        this.observer = observer;
    }
}
