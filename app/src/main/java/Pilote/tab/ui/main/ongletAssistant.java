package Pilote.tab.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import Pilote.tab.Constante;
import Pilote.tab.Main;
import Pilote.tab.R;

public class ongletAssistant extends Fragment {

    private static View view;

    private NumberPicker picker1;
    private NumberPicker picker2;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate( R.layout.onglet_assistant, container, false);
        return root;
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.view = view;

        view.findViewById( R.id.btn_valide).setOnClickListener(valide_Listener);

        picker1 = view.findViewById( R.id.picker1);
        picker1.setMinValue(0);
        picker1.setMaxValue(200);
        picker1.setValue( 80 );

        picker2 = view.findViewById( R.id.picker2);
        picker2.setMinValue(0);
        picker2.setMaxValue(20);
        picker2.setValue( 11 );

        ((TextView) view.findViewById(R.id.text_result)).setText( "Kp : ?\n");
        ((TextView) view.findViewById(R.id.text_result)).append(  "Ki : ?\n");
        ((TextView) view.findViewById(R.id.text_result)).append(  "Kd : ?" );

    };

    private View.OnClickListener valide_Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            double Ti,Td,Kp,Ki,Kd;

            Kp = 0.2 * picker1.getValue();
            Ti = (double)picker2.getValue()/2;
            Td = (double)picker2.getValue()/3;
            Ki = Kp/Ti;
            Kd = Kp*Td;

            TextView result = view.findViewById(R.id.text_result);
            result.setText( "Kp : " + (int)Math.round( Kp ) + "\n");
            result.append(  "Ki : " + (int)Math.round( Ki ) + "\n");
            result.append(  "Kd : " + (int)Math.round( Kd ));

        }
    };

}
