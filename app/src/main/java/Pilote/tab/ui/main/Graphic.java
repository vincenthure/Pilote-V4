package Pilote.tab.ui.main;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.RelativeLayout;

public class Graphic extends RelativeLayout {

    private static Paint
        paint1,
        paint2;

    private static double
        cap            = Math.toRadians(0),
        capReel        = Math.toRadians(0),
        angleBarre     = Math.toRadians(0),
        angleBarreReel = Math.toRadians(0);


    private static boolean  extend,
                            retract;

    public Graphic(Context context) {
        super(context);
        paint1 = new Paint();
        paint1.setColor(Color.BLUE);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeWidth(4f);
        paint1.setAntiAlias(true);

        paint2 = new Paint();
        paint2.setColor(Color.MAGENTA);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(12f);
        paint2.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int xCenter    = getWidth()/2;
        int xGauche    = getWidth()/4;
        int xDroite    = getWidth()*3/4;
        int yCenter    = getHeight()*2/5;
        int yFooter    = getHeight()*7/8;
        int rayon      = getWidth()/3;


        int xCap       = (int)(Math.sin(cap) *rayon);
        int yCap       = (int)(Math.cos(cap) *rayon);

        int xBoat      = (int)(Math.sin(capReel)*rayon);
        int yBoat      = (int)(Math.cos(capReel)*rayon);
        int xBarreR    = (int)(Math.sin(angleBarreReel+capReel+Math.PI)*rayon*0.4);
        int yBarreR    = (int)(Math.cos(angleBarreReel+capReel+Math.PI)*rayon*0.4);
        int xPoupe     = xCenter - xBoat;
        int yPoupe     = yCenter + yBoat;

        int xBarre     = (int)(Math.sin(angleBarre+capReel+Math.PI)*rayon*0.4);
        int yBarre     = (int)(Math.cos(angleBarre+capReel+Math.PI)*rayon*0.4);


        //cap
        canvas.drawLine( xCenter, yCenter, xCenter+xCap, yCenter-yCap, paint1);
        //boat
        canvas.drawLine( xPoupe, yPoupe, xCenter + xBoat, yCenter - yBoat, paint2);
        //barre
        canvas.drawLine( xPoupe, yPoupe , xPoupe + xBarre, yPoupe - yBarre, paint1);
        //barre réel
        canvas.drawLine( xPoupe, yPoupe, xPoupe + xBarreR, yPoupe - yBarreR, paint2);


        if(retract) canvas.drawCircle( xGauche, yFooter,20, paint1);
          if(extend)  canvas.drawCircle( xDroite, yFooter,20, paint1);

        super.onDraw(canvas);
    }


    static public void setCap(int val){
        cap = Math.toRadians(val);
    }

    static public void setCapReel(int val){
        capReel= Math.toRadians(val); }

    static public void setAngleBarre(int val){
        angleBarre= Math.toRadians(val);
    }

    static public void setAngleBarreReel(int val){
        angleBarreReel= Math.toRadians(val);
    }

    static public void setExtend() {
        extend=true;
        retract=false;
    }

    static public void setRetract() {
        extend=false;
        retract=true;
    }

    static public void setStop() {
        extend=false;
        retract=false;
    }
}
