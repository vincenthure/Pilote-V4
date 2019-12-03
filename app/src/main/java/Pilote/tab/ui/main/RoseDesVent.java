package Pilote.tab.ui.main;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.RelativeLayout;

import Pilote.tab.R;

public class RoseDesVent extends RelativeLayout {

    Paint paint;


    public RoseDesVent(Context context) {
        super(context);
        paint= new Paint();
        paint.setColor(Color.LTGRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3f);
        paint.setAntiAlias(true);
        paint.setTextSize( 30 );
        paint.setTextAlign( Paint.Align.CENTER );
        //paint.setAlpha(0x80);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int xCenter    = getWidth()/2;
        int yCenter    = getHeight()*2/5;
        int yBas       = getHeight()*7/8;
        int tensionMax = getWidth()*5/12;

        int rayon   = getWidth()*5/13;
        int point45   = (int)(Math.sin( Math.toRadians(45) ) *rayon);
        int gap = 12;
        
        canvas.drawLine( xCenter, yCenter-rayon,xCenter, yCenter+rayon,paint);
        canvas.drawLine( xCenter-rayon, yCenter, xCenter+rayon, yCenter, paint);

        canvas.drawLine( xCenter-point45, yCenter-point45, xCenter+point45, yCenter+point45, paint);
        canvas.drawLine( xCenter-point45, yCenter+point45, xCenter+point45, yCenter-point45, paint);

        canvas.drawText( "N", xCenter, yCenter-rayon-gap, paint );
        canvas.drawText( "S", xCenter, yCenter+rayon+(3*gap), paint );
        canvas.drawText( "O", xCenter-rayon-(2*gap), yCenter+gap, paint );
        canvas.drawText( "E", xCenter+rayon+(2*gap), yCenter+gap, paint );

        canvas.drawText( "NO", xCenter-point45-(2*gap), yCenter-point45-gap, paint );
        canvas.drawText( "SO", xCenter-point45-(2*gap), yCenter+point45+(3*gap), paint );
        canvas.drawText( "NE", xCenter+point45+(2*gap), yCenter-point45-gap, paint );
        canvas.drawText( "SE", xCenter+point45+(2*gap), yCenter+point45+(3*gap), paint );

        canvas.drawCircle( xCenter, yCenter,rayon*8/10, paint);

        canvas.drawLine( xCenter-tensionMax, yBas, xCenter+tensionMax, yBas , paint);
        canvas.drawLine( xCenter-tensionMax, yBas+gap, xCenter-tensionMax, yBas-gap , paint);
        canvas.drawLine( xCenter, yBas+gap, xCenter, yBas-gap , paint);
        canvas.drawLine( xCenter+tensionMax, yBas+gap, xCenter+tensionMax, yBas-gap , paint);
        canvas.drawText( "100%", xCenter+tensionMax, yBas-(2*gap), paint );
        canvas.drawText( "0%", xCenter, yBas-(2*gap), paint );
        canvas.drawText( "100%", xCenter-tensionMax, yBas-(2*gap), paint );
        super.onDraw(canvas);
    }
}
