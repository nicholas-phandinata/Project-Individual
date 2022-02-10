package id.nicholasp.projectindividual;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputLayout;

public class HomeFragment extends Fragment implements View.OnClickListener{

    Spinner spinner;
    Button button_save;
    RadioGroup radioGroup;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        button_save = view.findViewById(R.id.button_save);

        EditText layout_nama = view.findViewById(R.id.home_edit_name);
        EditText layout_email = view.findViewById(R.id.home_edit_email);
        RadioButton yes = (RadioButton) view.findViewById(R.id.radioButton_ya);
        RadioButton no = (RadioButton) view.findViewById(R.id.radioButton_tidak);
        spinner = (Spinner) view.findViewById(R.id.home_spinner);

        radioGroup = view.findViewById(R.id.id_radioGroup);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String res = null;
                Log.d("nama", String.valueOf(layout_nama.getText()));
                String nama = String.valueOf(layout_nama.getText());
                String email = String.valueOf(layout_email.getText());

                String spinner_val = spinner.getSelectedItem().toString();

                if(yes.isChecked()){
                    res = "Yes";
                }
                else if(no.isChecked()){
                    res = "No";
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Your Data");
                builder.setMessage("Nama : " + nama +
                        "\nEmail: " + email +
                        "\nJenis Kelamin: " + spinner_val +
                        "\nSubscribe : " + res);
                builder.setIcon(getResources().getDrawable(android.R.drawable.ic_dialog_info));
                builder.setCancelable(false);
                builder.setNegativeButton("Cancel",null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == button_save){
//            Log.d("email", String.valueOf(layout_nama));
        }
    }
}