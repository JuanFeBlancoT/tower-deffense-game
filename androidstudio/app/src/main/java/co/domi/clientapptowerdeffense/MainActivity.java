package co.domi.clientapptowerdeffense;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements  MsgListener{

    private ImageView mapImg;
    private Button addBtn;
    private boolean waitingP;
    private Communication com;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapImg = findViewById(R.id.mapId);
        addBtn = findViewById(R.id.addTowerId);

        com = Communication.ComgetInstance();
        com.setObserver(this);
        com.start();

        addBtn.setOnClickListener(
                (view)->{

                }
        );
    }

    @Override
    public void msgReceived(String msg) {

    }
}