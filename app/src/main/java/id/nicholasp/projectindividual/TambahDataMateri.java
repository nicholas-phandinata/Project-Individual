package id.nicholasp.projectindividual;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class TambahDataMateri extends AppCompatActivity implements View.OnClickListener {
    EditText edit_materi;
    Button btn_tambah_mat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_materi);

        edit_materi = findViewById(R.id.edit_nama_mat);

        btn_tambah_mat = findViewById(R.id.btn_tambah_mat);

        btn_tambah_mat.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        tambahMateri();
        onBackPressed();
    }

    private void tambahMateri() {
        final String materi = edit_materi.getText().toString().trim();

        class TambahMateri extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahDataMateri.this,
                        "Menambah Data...", "Harap menunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Konfigurasi.KEY_MAT_NAMA, materi);

                HttpHandler handler = new HttpHandler();
                String result = handler.sendPostRequest(Konfigurasi.URL_ADD_MAT, hashMap);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
            }
        }
        TambahMateri tm = new TambahMateri();
        tm.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        onBackPressed();
        return super.onCreateOptionsMenu(menu);
    }
}