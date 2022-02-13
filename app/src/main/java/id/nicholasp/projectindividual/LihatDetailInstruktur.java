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

public class LihatDetailInstruktur extends AppCompatActivity {
    EditText edit_id_ins, edit_nama_ins, edit_email_ins, edit_hp_ins;
    String id;
    Button button_update;
    Button button_delete;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_detail_instruktur);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        edit_id_ins = findViewById(R.id.edit_id_ins);
        edit_nama_ins = findViewById(R.id.edit_nama_ins);
        edit_email_ins = findViewById(R.id.edit_email_ins);
        edit_hp_ins = findViewById(R.id.edit_hp_ins);

        //menerima intent dari class
        Intent receiveIntent = getIntent();
        id = receiveIntent.getStringExtra(Konfigurasi.INS_ID);
        edit_id_ins.setText(id);

        //button
        button_update = findViewById(R.id.btn_update_instruktur);
        button_delete = findViewById(R.id.btn_delete_instruktur);

        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInstruktur();
            }
        });
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LihatDetailInstruktur.this);
                alertDialogBuilder.setMessage("Yakin Delete?");

                alertDialogBuilder.setPositiveButton("Ya",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteInstruktur();
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

    private void deleteInstruktur() {
        class DeleteInstruktur extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailInstruktur.this,
                        "Deleting Data...", "Harap menunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... params) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_DELETE_INS, id);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                displayDetailData(message);
                Intent intent = new Intent(LihatDetailInstruktur.this, MainActivity.class);
                intent.putExtra("KeyName", "instruktur");
                startActivity(intent);
            }
        }
        DeleteInstruktur de = new DeleteInstruktur();
        de.execute();
    }

    private void updateInstruktur() {
        final String nama_ins = edit_nama_ins.getText().toString().trim();
        final String email_ins = edit_email_ins.getText().toString().trim();
        final String hp_ins = edit_hp_ins.getText().toString().trim();

        class UpdateInstruktur extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailInstruktur.this,
                        "Updating Data...", "Harap menunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Konfigurasi.KEY_INS_ID, id);
                hashMap.put(Konfigurasi.KEY_INS_NAMA, nama_ins);
                hashMap.put(Konfigurasi.KEY_INS_EMAIL, email_ins);
                hashMap.put(Konfigurasi.KEY_INS_HP, hp_ins);

                HttpHandler handler = new HttpHandler();
                String result = handler.sendPostRequest(Konfigurasi.URL_UPDATE_INS, hashMap);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                displayDetailData(message);
            }
        }

        UpdateInstruktur ue = new UpdateInstruktur();
        ue.execute();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LihatDetailInstruktur.this);
        alertDialogBuilder.setMessage("Update lagi?");

        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(LihatDetailInstruktur.this, MainActivity.class);
                        intent.putExtra("KeyName", "instruktur");
                        startActivity(intent);
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> { //inner class
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailInstruktur.this,
                        "Mengambil Data", "Harap menunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_DETAIL_INS, id);
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

            String nama_ins = object.getString(Konfigurasi.TAG_JSON_INS_NAMA);
            String email_ins = object.getString(Konfigurasi.TAG_JSON_INS_EMAIL);
            String hp_ins = object.getString(Konfigurasi.TAG_JSON_INS_HP);

            edit_nama_ins.setText(nama_ins);
            edit_email_ins.setText(email_ins);
            edit_hp_ins.setText(hp_ins);

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