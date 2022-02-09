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

public class TambahDataPeserta extends AppCompatActivity implements View.OnClickListener{
    EditText edit_nama_pst, edit_email_pst, edit_hp_pst, edit_ins_pst;
    Button btn_tambah_ins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_peserta);

        edit_nama_pst = findViewById(R.id.edit_nama_pst);
        edit_email_pst = findViewById(R.id.edit_email_pst);
        edit_hp_pst = findViewById(R.id.edit_hp_pst);
        edit_ins_pst = findViewById(R.id.edit_ins_pst);
        btn_tambah_ins = findViewById(R.id.btn_tambah_ins);

        btn_tambah_ins.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        tambahPeserta();
        onBackPressed();
    }

    private void tambahPeserta() {
        final String nama_pst = edit_nama_pst.getText().toString().trim();
        final String email_pst = edit_email_pst.getText().toString().trim();
        final String hp_pst = edit_hp_pst.getText().toString().trim();
        final String ins_pst = edit_ins_pst.getText().toString().trim();

        class TambahPeserta extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahDataPeserta.this,
                        "Menambah Data...", "Harap menunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Konfigurasi.KEY_PST_NAMA, nama_pst);
                hashMap.put(Konfigurasi.KEY_PST_EMAIL, email_pst);
                hashMap.put(Konfigurasi.KEY_PST_HP, hp_pst);
                hashMap.put(Konfigurasi.KEY_PST_INSTANSI, ins_pst);

                HttpHandler handler = new HttpHandler();
                String result = handler.sendPostRequest(Konfigurasi.URL_ADD_PST, hashMap);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
            }
        }
        TambahPeserta tp = new TambahPeserta();
        tp.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        onBackPressed();
        return super.onCreateOptionsMenu(menu);
    }
}