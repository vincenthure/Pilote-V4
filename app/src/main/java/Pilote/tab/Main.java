package Pilote.tab;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import Pilote.tab.ui.main.Graphic;
import Pilote.tab.ui.main.SectionsPagerAdapter;
import Pilote.tab.ui.main.ongletCap;
import Pilote.tab.ui.main.ongletReglages;
import Pilote.tab.ui.main.ongletVisuel;
import Pilote.tab.ui.main.ongletAssistant;



public class Main extends AppCompatActivity {

    private static final String HC05_MAC = Constante.Adresse_Mac;
    private static final UUID HC05_UUID = UUID.fromString( Constante.UUID );
    private static final int REQUEST_ENABLE_BT = 1;

    private static BluetoothAdapter bta;                  //bluetooth stuff
    private static BluetoothSocket mmSocket;              //bluetooth stuff
    private static BluetoothDevice mmDevice;              //bluetooth stuff
    private static ConnectedThread btt = null;            //Our custom thread
    public static Context context;
    private static long last_time=0;
    public Handler mHandler;                              //this receives messages from thread

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context =this;
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter( this, getSupportFragmentManager() );
        ViewPager viewPager = findViewById( R.id.view_pager );
        viewPager.setAdapter( sectionsPagerAdapter );
        TabLayout tabs = findViewById( R.id.tabs );
        tabs.setupWithViewPager( viewPager );

        bta = BluetoothAdapter.getDefaultAdapter();
        //if bluetooth is not enabled then create Intent for user to turn it on
        if (!bta.isEnabled()) {
            Intent enableBTIntent = new Intent( BluetoothAdapter.ACTION_REQUEST_ENABLE );
            startActivityForResult( enableBTIntent, REQUEST_ENABLE_BT );
        }
        else {
            initiateBluetoothProcess();
            // infinite loop every 2 seconds to check that connection is available
            check_connection();
        }
    }

    public void initiateBluetoothProcess() {
        if (bta.isEnabled()) {
            //attempt to connect to bluetooth module
            BluetoothSocket tmp = null;

            mmDevice = bta.getRemoteDevice( HC05_MAC );
            //create socket

            try {
                tmp = mmDevice.createRfcommSocketToServiceRecord( HC05_UUID );
                mmSocket = tmp;
                mmSocket.connect();
                Log.i( "[BLUETOOTH]", "socket connected" + mmDevice.getName() );
            } catch (IOException e) {
                try {
                    mmSocket.close();
                } catch (IOException c) {
                    return;
                }
            }

            Log.i( "[BLUETOOTH]", "Creating handler" );
            mHandler = new Handler( Looper.getMainLooper() ) {
                @Override
                public void handleMessage(Message msg) {
                    //super.handleMessage(msg);
                    if(msg.what == ConnectedThread.RESPONSE_MESSAGE) {
                        String txt = (String) msg.obj;
                        decode( txt );
                    }
                }
            };

            Log.i( "[BLUETOOTH]", "Creating and running Thread" );
            btt = new ConnectedThread( mmSocket, mHandler );
            btt.start();
        }
    }

    private void check_connection() {
        TimerTask check_last_time = new TimerTask() {
            public void run() {
                long gap =System.currentTimeMillis()-last_time;

                if(gap>1000) {
                    initiateBluetoothProcess();
                    Log.i( "[BLUETOOTH]", "Connection down" );
                }
            }
        };
        Timer timer = new Timer("Timer");

        long delay  = 3000L;
        long period = 2000L;
        timer.scheduleAtFixedRate(check_last_time, delay, period);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult( requestCode, resultCode, data );
        if (resultCode == RESULT_OK && requestCode == REQUEST_ENABLE_BT) {
            initiateBluetoothProcess();
        }
    }

    public static void send(char c) {

        if (mmSocket.isConnected() && btt != null) {

            byte b[]={(byte)c};
            btt.write(b);
        }
        else {
            Toast.makeText( context, R.string.disconnected, Toast.LENGTH_LONG ).show();
        }
    }

    public static void decode(String line) {


        last_time= System.currentTimeMillis();
        char index = line.charAt( 0 );
        int param = 0;
        if (line.length()>1) {
            try {
                param = Integer.parseInt( line.substring( 1 ) );
            } catch (Exception e) {
                Log.i( "vincent", "Error : parameter not integer" );
                return;
            }
        }
        switch (index) {
            case Constante.pause:
                Bip.ring();
                ongletCap.setPause(  );
                break;

            case Constante.actif:
                Bip.ring();
                ongletCap.setActif(  );
                break;

            case Constante.cap:
                Bip.ring();
                ongletCap.setCap( param );
                Graphic.setCap( param );
                break;

            case Constante.kp:
                Bip.ring();
                ongletReglages.setKp( param );
                break;

            case Constante.ki:
                Bip.ring();
                ongletReglages.setKi( param );
                break;

            case Constante.kd:
                Bip.ring();
                ongletReglages.setKd( param );
                break;

            case Constante.kp2:
                Bip.ring();
                ongletReglages.setKp2( param );
                break;

            case Constante.ki2:
                Bip.ring();
                ongletReglages.setKi2( param );
                break;

            case Constante.kd2:
                Bip.ring();
                ongletReglages.setKd2( param );
                break;

            case Constante.barreMax:
                Bip.ring();
                ongletReglages.setBarreMax( param );
                break;

            case Constante.tensionMoteur:
                Graphic.setTensionMoteur( param  );
                break;

            case Constante.capReel:
                Graphic.setCapReel( param );
                break;

            case Constante.angleBarre:
                Graphic.setAngleBarre( param );
                break;

            case Constante.angleBarreReel:
                Graphic.setAngleBarreReel( param );
                break;

            case Constante.redraw:
                ongletVisuel.redrawGraphic();
                break;
        }
    }
}