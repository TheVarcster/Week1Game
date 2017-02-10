package andreasphillips.week1game.gdf.mgms.fullsail.edu.week1game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

   private  Button btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = (Button)findViewById(R.id.btnPlay);
        btnPlay.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == event.ACTION_DOWN && v.getId() == R.id.btnPlay) {
            Intent i = new Intent(this, GameActivity.class);
            //i.putExtra("GameMode", 0);
            startActivity(i);
            finish();
        }
        return false;
    }
}
