package soccergame.assignment2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.jar.Attributes;

/**
 * Created by Abhinav on 9/25/2015.
 */
public class SurfaceField extends SurfaceView{
    public SurfaceField(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    //Draw field
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint white = new Paint();
        white.setColor(Color.WHITE);
        canvas.drawRect(0,300,80,500,white);
        canvas.drawRect( 1730,300, 1850, 500, white);
        canvas.drawCircle(900,420,50,white);
        canvas.drawLine(900,0,900,900,white);
    }
}