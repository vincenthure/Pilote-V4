package Pilote.tab;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;


public class Peripherique extends Thread {

    private String adresse;
    private BluetoothDevice device;
    private String nom;
    private Handler handler;

    private BluetoothSocket socket = null;
    private InputStream receiveStream = null;
    private OutputStream sendStream = null;
    private TReception tReception;
    public final static int CODE_CONNEXION = 0;
    public final static int CODE_RECEPTION = 1;
    public final static int CODE_DECONNEXION = 2;

    public Peripherique(BluetoothDevice device, Handler handler) {
        if (device != null) {
            this.device = device;
            this.nom = device.getName();
            this.adresse = device.getAddress();
            this.handler = handler;
        }
        else {
            this.device = device;
            this.nom = "Aucun";
            this.adresse = "";
            this.handler = handler;
        }

        try {
            socket = device.createRfcommSocketToServiceRecord( UUID.fromString( "00001101-0000-1000-8000-00805F9B34FB" ) );
            receiveStream = socket.getInputStream();
            sendStream = socket.getOutputStream();
        }
        catch (IOException e) {
            e.printStackTrace();
            socket = null;
        }

        if (socket != null)
            tReception = new TReception( handler );

 
    }

    public void connecter()
    {
        new Thread()
        {
            @Override public void run()
            {
                try
                {
                    socket.connect();

                    Message msg = Message.obtain();
                    msg.arg1 = CODE_CONNEXION;
                    handler.sendMessage(msg);

                    tReception.start();

                }
                catch (IOException e)
                {
                    System.out.println("<Socket> error connect");
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public boolean deconnecter()
    {
        try
        {
            tReception.arreter();

            socket.close();
            return true;
        }
        catch (IOException e)
        {
            System.out.println("<Socket> error close");
            e.printStackTrace();
            return false;
        }
    }

    public void envoyer(String data)
    {
        if(socket == null)
            return;

        try
        {
            sendStream.write(data.getBytes());
            sendStream.flush();
        }
        catch (IOException e)
        {
            System.out.println("<Socket> error send");
            e.printStackTrace();
        }
    }

    private class TReception extends Thread {
        Handler handlerUI;
        private boolean fini;


        TReception(Handler h) {
            handlerUI = h;
            fini = false;
        }

        @Override
        public void run() {
            while (!fini) {
                try {
                    if (receiveStream.available() > 0) {
                        byte buffer[] = new byte[100];
                        int k = receiveStream.read( buffer, 0, 100 );

                        if (k > 0) {
                            byte rawdata[] = new byte[k];
                            for (int i = 0; i < k; i++)
                                rawdata[i] = buffer[i];

                            String data = new String( rawdata );

                            Message msg = Message.obtain();
                            msg.what = Peripherique.CODE_RECEPTION;
                            msg.obj = data;
                            handlerUI.sendMessage( msg );
                        }
                    }
                    try {
                        Thread.sleep( 250 );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    //System.out.println("<Socket> error read");
                    e.printStackTrace();
                }
            }
        }

        public void arreter() {
            if (fini == false) {
                fini = true;
            }
            try {
                Thread.sleep( 250 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}