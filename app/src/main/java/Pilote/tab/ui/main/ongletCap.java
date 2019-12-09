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

public class ongletCap extends Fragment
    {
        private static View view;

        @Override
        public View onCreateView(
                LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View root = inflater.inflate(R.layout.onglet_cap, container, false);
            return root;
        };

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            this.view = view;

            view.findViewById( R.id.btn_moins_10).setOnClickListener(cap_moins_10_Listener);
            view.findViewById( R.id.btn_plus_10).setOnClickListener(cap_plus_10_Listener);
            view.findViewById( R.id.btn_moins_1).setOnClickListener(cap_moins_1_Listener);
            view.findViewById( R.id.btn_plus_1).setOnClickListener(cap_plus_1_Listener);
            view.findViewById( R.id.btnStanby).setOnClickListener(stanby_Listener);
            view.findViewById( R.id.cap).setOnClickListener(cap_Listener);
            Main.send( Constante.cap_request );
        };

        private View.OnClickListener cap_Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main.send( Constante.cap_request);
            }
        };

        private View.OnClickListener cap_moins_10_Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main.send( Constante.cap_moins_10 );
            }
        };

        private View.OnClickListener cap_plus_10_Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main.send( Constante.cap_plus_10 );
            }
        };

        private View.OnClickListener cap_moins_1_Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main.send( Constante.cap_moins_1 );
            }
        };

        private View.OnClickListener cap_plus_1_Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main.send( Constante.cap_plus_1 );
            }
        };

        private View.OnClickListener stanby_Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main.send( Constante.stanby );
            }
        };

        static public void setCap( int val) {
            ((TextView) view.findViewById(R.id.textCap)).setText( val + "°" );
        };

        static public void setinfo( String str) {
            //((TextView) view.findViewById(R.id.textInfo)).setText( str );
        };

        static public void setPause() {
            ((TextView) view.findViewById(R.id.cap)).setText(R.string.pause);
        };

        static public void setActif() {
            ((TextView) view.findViewById(R.id.cap)).setText(R.string.cap);
        };
    }

