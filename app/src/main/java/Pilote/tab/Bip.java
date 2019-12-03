package Pilote.tab;

import android.media.AudioManager;
import android.media.ToneGenerator;


public class Bip {

    private static ToneGenerator toneG = new ToneGenerator( AudioManager.STREAM_ALARM, 50);

    public static void ring(){
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
    }
}
