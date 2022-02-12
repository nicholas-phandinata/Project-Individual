package id.nicholasp.projectindividual;

import android.app.AlertDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;


public class HomeFragment extends Fragment implements View.OnClickListener{

    Spinner spinner;
    Button button_submit;
    RadioGroup radioGroup;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        button_submit = view.findViewById(R.id.button_submit);

        EditText lokasi = view.findViewById(R.id.home_edit_lokasi);
        EditText domisili = view.findViewById(R.id.home_edit_domisili);
        RadioButton ya = view.findViewById(R.id.radioButton_ya);
        RadioButton belum = view.findViewById(R.id.radioButton_belum);
        spinner = view.findViewById(R.id.home_spinner);

        radioGroup = view.findViewById(R.id.id_radioGroup);
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String res = null;
                String lok = String.valueOf(lokasi.getText());
                String dom = String.valueOf(domisili.getText());

                String spinner_val = spinner.getSelectedItem().toString();

                if(ya.isChecked()){
                    res = "Ya";
                }
                else if(belum.isChecked()){
                    res = "Belum";
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Data Yang Akan Disubmit");
                builder.setMessage("Lokasi Terakhir :  " + lok +
                        "\nDomisili              :  " + dom +
                        "\nKondisi Badan   :  " + spinner_val +
                        "\nSudah Vaksin    :  " + res);
                builder.setIcon(getResources().getDrawable(android.R.drawable.ic_dialog_info));
                builder.setCancelable(false);
                builder.setPositiveButton("OK",null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == button_submit){
        }
    }
}