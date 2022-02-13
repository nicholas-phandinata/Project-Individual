package id.nicholasp.projectindividual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class LihatDetailKelas extends AppCompatActivity {
    EditText edit_id_kls, edit_mulai_kelas, edit_akhir_kelas, edit_nama_instruktur, edit_nama_materi;
    String id;
    Button button_update_kelas;
    Button button_delete_kelas;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_detail_kelas);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        edit_id_kls = findViewById(R.id.edit_id_kls);
        edit_mulai_kelas = findViewById(R.id.edit_mulai_kelas);
        edit_akhir_kelas = findViewById(R.id.edit_akhir_kelas);
        edit_nama_instruktur = findViewById(R.id.edit_nama_instruktur);
        edit_nama_materi = findViewById(R.id.edit_nama_materi);

        //menerima intent dari class
        Intent receiveIntent = getIntent();
        id = receiveIntent.getStringExtra(Konfigurasi.KLS_ID);
        edit_id_kls.setText(id);

        //button
        button_update_kelas = findViewById(R.id.btn_update_kelas);
        button_delete_kelas = findViewById(R.id.btn_delete_kelas);

        button_update_kelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LihatDetailKelas.this, UpdateKelas.class);
                intent.putExtra(Konfigurasi.KLS_ID, id);
                startActivity(intent);
            }
        });
        button_delete_kelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LihatDetailKelas.this);
                alertDialogBuilder.setMessage("Yakin Delete?");

                alertDialogBuilder.setPositiveButton("Ya",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteKelas();
                            }
                        });

                alertDialogBuilder.setNegativeButton("Tidak",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        //mengambil data JSON
        getJSON();
    }

    private void deleteKelas() {
        class DeleteKelas extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailKelas.this,
                        "Deleting Data...", "Harap menunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... params) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_DELETE_KLS, id);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                displayDetailData(message);
                Intent intent = new Intent(LihatDetailKelas.this, MainActivity.class);
                intent.putExtra("KeyName", "kelas");
                startActivity(intent);
            }
        }
        DeleteKelas dk = new DeleteKelas();
        dk.execute();
    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> { //inner class
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailKelas.this,
                        "Mengambil Data", "Harap menunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_DETAIL_KLS, id);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                displayDetailData(message);
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void displayDetailData(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            JSONObject object = result.getJSONObject(0);

            String id_kls = object.getString(Konfigurasi.TAG_JSON_KLS_ID);
            String mulai = object.getString(Konfigurasi.TAG_JSON_KLS_TGL_MULAI);
            String akhir = object.getString(Konfigurasi.TAG_JSON_KLS_TGL_AKHIR);
            String n_ins = object.getString(Konfigurasi.TAG_JSON_KLS_INS);
            String n_mat = object.getString(Konfigurasi.TAG_JSON_KLS_MAT);

            edit_id_kls.setText(id_kls);
            edit_mulai_kelas.setText(mulai);
            edit_akhir_kelas.setText(akhir);
            edit_nama_instruktur.setText(n_ins);
            edit_nama_materi.setText(n_mat);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}