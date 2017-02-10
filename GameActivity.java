package andreasphillips.week1game.gdf.mgms.fullsail.edu.week1game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.ConnectEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.MatchedRoomsEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomData;
import com.shephertz.app42.gaming.multiplayer.client.listener.ConnectionRequestListener;

public class GameActivity extends AppCompatActivity implements ConnectionRequestListener, View.OnTouchListener {

    private WarpClient theClient;
    private Button btnConnect;

    public boolean isConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        btnConnect = (Button)findViewById(R.id.btnConnect);
        btnConnect.setOnTouchListener(this);

        init();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == event.ACTION_DOWN) {
            if(v.getId() == R.id.btnConnect) {
                theClient.connectWithUserName("Andreas");
            }
        }
        return false;
    }

    public void onBackPressed() {
        super.onBackPressed();

        theClient.removeConnectionRequestListener(this);
        if(theClient != null && isConnected) {
            isConnected = false;
            theClient.disconnect();
        }

    }

    private void init() {
        WarpClient.initialize(Constants.apiKey, Constants.secretKey);

        try {
            theClient = WarpClient.getInstance();
        }
        catch(Exception ex) {
            Toast.makeText(getApplicationContext(), "initialization exception", Toast.LENGTH_SHORT).show();
        }

        theClient.addConnectionRequestListener(this);
    }

    public void onConnectDone(final ConnectEvent event) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(event.getResult() == WarpResponseResultCode.SUCCESS) {
                    isConnected = true;
                    //theClient.getRoomInRange(0, 1);
                    Toast.makeText(getApplicationContext(), "connected", Toast.LENGTH_SHORT).show();

                }
                else {
                    isConnected = false;
                    Toast.makeText(getApplicationContext(), "connection failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDisconnectDone(ConnectEvent connectEvent) {
        isConnected = false;
    }

    @Override
    public void onInitUDPDone(byte b) {

    }


/*
    @Override
    public void onGetMatchedRoomsDone(final MatchedRoomsEvent event) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RoomData[] roomDataList = event.getRoomsData();
                if(roomDataList.length>0){
                    roomlistAdapter.setData(roomDataList);
                    listView.setAdapter(roomlistAdapter);
                }else{
                    roomlistAdapter.clear();
                }
            }
        });
    }
*/


}
