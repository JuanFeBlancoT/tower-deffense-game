package co.domi.clientapptowerdeffense;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements  MsgListener{

    private ImageView mapImg;
    private Button addBtn;
    private boolean waitingP;
    private Communication com;
    //coordinates phone when clicked
    private int px, py;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        waitingP = false;
        mapImg = findViewById(R.id.mapId);
        addBtn = findViewById(R.id.addTowerId);

        com = Communication.ComgetInstance();
        com.setObserver(this);
        com.start();

        addBtn.setOnClickListener(
                (view)->{
                    waitingP = !waitingP;
                }
        );

        mapImg.setOnTouchListener(
                (view, event)->{
                        if(event.getAction() == MotionEvent.ACTION_DOWN){
                            px = (int) event.getX();
                            py = (int) event.getY();
                            if(waitingP){
                                com.sendMessage(px+";"+py+"\n");
                            }

                        }
                    return false;
                }
        );
    }

    @Override
    public void msgReceived(String msg) {

    }
}