package Pilote.tab.ui.main;

import androidx.fragment.app.Fragment;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;


public class ongletVisuel extends Fragment
    {
        private static
            View view;

        private static boolean created=false;

        private static
            Graphic graphic;

        @Override
        public View onCreateView
                (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            RoseDesVent layout = new RoseDesVent(getActivity());
            layout.setBackgroundColor(Color.WHITE);
            layout.setLayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));

            graphic = new Graphic(getActivity());
            graphic.setBackgroundColor(Color.TRANSPARENT);
            graphic.setLayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));
            layout.addView( graphic );
            created=true;
            return layout;
        };

        static public void redrawGraphic() {
            if (created == true) {
                graphic.invalidate();
            }
        }
    }

