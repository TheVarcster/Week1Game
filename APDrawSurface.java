package andreasphillips.week1game.gdf.mgms.fullsail.edu.week1game;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by andreas on 2/8/17.
 */

public class APDrawSurface extends SurfaceView implements SurfaceHolder.Callback {

    private TutorialThread _thread;

    public APDrawSurface(Context context) {
        super(context);
        getHolder().addCallback(this);
        _thread = new TutorialThread(getHolder(), this);
    }
    public APDrawSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        _thread = new TutorialThread(getHolder(), this);
    }
    public APDrawSurface(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        getHolder().addCallback(this);
        _thread = new TutorialThread(getHolder(), this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        _thread.setRunning(true);
        _thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
class TutorialThread extends Thread {
    private SurfaceHolder _surfaceHolder;
    private APDrawSurface _panel;
    private boolean _run = false;

    public TutorialThread(SurfaceHolder surfaceHolder, APDrawSurface panel) {
        _surfaceHolder = surfaceHolder;
        _panel = panel;
    }

    public void setRunning(boolean run) {
        _run = run;
    }

    @Override
    public void run() {
        Canvas c;
        while (_run) {
            c = null;
            try {
                c = _surfaceHolder.lockCanvas(null);
                synchronized (_surfaceHolder) {
                    _panel.onDraw(c);
                }
            } finally {
                if (c != null) {
                    _surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }
}