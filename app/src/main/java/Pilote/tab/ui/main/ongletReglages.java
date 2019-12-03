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
        view.findViewById( R.id.btn_kp2_moins).setOnClickListener(kp2_moins_Listener);
        view.findViewById( R.id.btn_kp2_plus).setOnClickListener(kp2_plus_Listener);
        view.findViewById( R.id.btn_ki2_moins).setOnClickListener(ki2_moins_Listener);
        view.findViewById( R.id.btn_ki2_plus).setOnClickListener(ki2_plus_Listener);
        view.findViewById( R.id.btn_kd2_moins).setOnClickListener(kd2_moins_Listener);
        view.findViewById( R.id.btn_kd2_plus).setOnClickListener(kd2_plus_Listener);
        view.findViewById( R.id.btn_barre_moins).setOnClickListener(barre_moins_Listener);
        view.findViewById( R.id.btn_barre_plus).setOnClickListener(barre_plus_Listener);
        view.findViewById( R.id.reset).setOnClickListener(reset_Listener);
        view.findViewById( R.id.pidCap).setOnClickListener(pid_Listener);
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

    private View.OnClickListener kp2_moins_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Main.send( Constante.kp2_moins );
        }
    };

    private View.OnClickListener kp2_plus_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Main.send( Constante.kp2_plus );
        }
    };

    private View.OnClickListener ki2_moins_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Main.send( Constante.ki2_moins );
        }
    };

    private View.OnClickListener ki2_plus_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Main.send( Constante.ki2_plus );
        }
    };

    private View.OnClickListener kd2_moins_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Main.send( Constante.kd2_moins );
        }
    };

    private View.OnClickListener reset_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Main.send( Constante.reset_pid );
        }
    };

    private View.OnClickListener kd2_plus_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Main.send( Constante.kd2_plus );
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

    static public void setKp2( int val) {

        ((TextView) view.findViewById(R.id.text_Kp2)).setText( R.string.kp );
        ((TextView) view.findViewById(R.id.text_Kp2)).append( " "+val );
    };

    static public void setKi2( int val) {

        ((TextView) view.findViewById(R.id.text_Ki2)).setText( R.string.ki );
        ((TextView) view.findViewById(R.id.text_Ki2)).append( " "+val );
    };

    static public void setKd2( int val) {

        ((TextView) view.findViewById(R.id.text_Kd2)).setText( R.string.kd );
        ((TextView) view.findViewById(R.id.text_Kd2)).append( " "+val );
    };

    static public void setBarreMax( int val) {

        ((TextView) view.findViewById(R.id.text_BarreMax)).setText( R.string.max );
        ((TextView) view.findViewById(R.id.text_BarreMax)).append( " "+val );
    };
}
