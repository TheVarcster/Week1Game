package andreasphillips.week1game.gdf.mgms.fullsail.edu.week1game;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity implements View.OnTouchListener {

    private Button btnCred;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        btnCred = (Button)findViewById(R.id.btnCredits);
        btnCred.setOnTouchListener(this);


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == event.ACTION_DOWN) {
            if (v.getId() == R.id.btnCredits) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Made by");
                dialog.setMessage("Andreas Phillips\nMGMS | GDF\n02/08/2017");
                dialog.setPositiveButton(" OK ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            } else {
                Intent i = new Intent(this, GameActivity.class);
                startActivity(i);
                finish();
            }
        }
        return false;
    }
}
