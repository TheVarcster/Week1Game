package andreasphillips.week1game.gdf.mgms.fullsail.edu.week1game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == event.ACTION_DOWN) {
            Intent i = new Intent(this, GameActivity.class);
            //i.putExtra("GameMode", 0);
            startActivity(i);
            finish();
        }
        return false;
    }
}
