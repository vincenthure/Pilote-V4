package Pilote.tab.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import Pilote.tab.Constante;
import Pilote.tab.Main;
import Pilote.tab.R;

public class ongletReglages extends Fragment {

    private static View view;

    @Override
    public View onCreateView
            (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.onglet_reglages, container, false);
        return root;
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.view = view;

        view.findViewById( R.id.btn_kp_moins).setOnClickListener(kp_moins_Listener);
        view.findViewById( R.id.btn_kp_plus).setOnClickListener(kp_plus_Listener);
        view.findViewById( R.id.btn_ki_moins).setOnClickListener(ki_moins_Listener);
        view.findViewById( R.id.btn_ki_plus).setOnClickListener(ki_plus_Listener);
        view.findViewById( R.id.btn_kd_moins).setOnClickListener(kd_moins_Listener);
        view.findViewById( R.id.btn_kd_plus).setOnClickListener(kd_plus_Listener);
        view.findViewById( R.id.btn_barre_moins).setOnClickListener(barre_moins_Listener);
        view.findViewById( R.id.btn_barre_plus).setOnClickListener(barre_plus_Listener);
        view.findViewById( R.id.reset).setOnClickListener(reset_Listener);
        view.findViewById( R.id.pidCap).setOnClickListener(pid_Listener);
        view.findViewById( R.id.btn_threshold_moins).setOnClickListener(threshold_moins_Listener);
        view.findViewById( R.id.btn_threshold_plus).setOnClickListener(threshold_plus_Listener);

        Main.send( Constante.pid_request );
    };

    private View.OnClickListener pid_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Main.send( Constante.pid_request );
        }
    };

    private View.OnClickListener kp_moins_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Main.send( Constante.kp_moins );
        }
    };

    private View.OnClickListener kp_plus_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Main.send( Constante.kp_plus );
        }
    };

    private View.OnClickListener ki_moins_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Main.send( Constante.ki_moins );
        }
    };

    private View.OnClickListener ki_plus_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Main.send( Constante.ki_plus );
        }
    };

    private View.OnClickListener kd_moins_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Main.send( Constante.kd_moins );
        }
    };

    private View.OnClickListener kd_plus_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Main.send( Constante.kd_plus );
        }
    };

    private View.OnClickListener reset_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Main.send( Constante.reset_pid );
        }
    };

    private View.OnClickListener barre_moins_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Main.send( Constante.barre_max_moins );
        }
    };

    private View.OnClickListener barre_plus_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Main.send( Constante.barre_max_plus );
        }
    };

    private View.OnClickListener threshold_moins_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Main.send( Constante.threshold_moins );
        }
    };

    private View.OnClickListener threshold_plus_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Main.send( Constante.threshold_plus );
        }
    };

    static public void setKp( int val) {

        ((TextView) view.findViewById(R.id.text_Kp)).setText( R.string.kp );
        ((TextView) view.findViewById(R.id.text_Kp)).append( " "+val );
    };

    static public void setKi( int val) {

        ((TextView) view.findViewById(R.id.text_Ki)).setText( R.string.ki );
        ((TextView) view.findViewById(R.id.text_Ki)).append( " "+val );
    };

    static public void setKd( int val) {

        ((TextView) view.findViewById(R.id.text_Kd)).setText( R.string.kd );
        ((TextView) view.findViewById(R.id.text_Kd)).append( " "+val );
    };

    static public void setBarreMax( int val) {

        ((TextView) view.findViewById(R.id.text_BarreMax)).setText( R.string.max );
        ((TextView) view.findViewById(R.id.text_BarreMax)).append( " "+val );
    };

    static public void setThreshold( int val) {
        ((TextView) view.findViewById(R.id.text_threshold)).setText( R.string.threshold );
       ((TextView) view.findViewById(R.id.text_threshold)).append( " "+Double.valueOf( val )/10);
    };
}
