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

public class LihatDetailMateri extends AppCompatActivity {
    EditText edit_id_mat, edit_nama_mat;
    String id;
    Button button_update_materi;
    Button button_delete_materi;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_detail_materi);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        edit_id_mat = findViewById(R.id.edit_id_mat);
        edit_nama_mat = findViewById(R.id.edit_nama_mat);

        //menerima intent dari class
        Intent receiveIntent = getIntent();
        id = receiveIntent.getStringExtra(Konfigurasi.MAT_ID);
        edit_id_mat.setText(id);

        //button
        button_update_materi = findViewById(R.id.btn_update_materi);
        button_delete_materi = findViewById(R.id.btn_delete_materi);

        button_update_materi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMateri();
            }
        });
        button_delete_materi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LihatDetailMateri.this);
                alertDialogBuilder.setMessage("Yakin Delete?");

                alertDialogBuilder.setPositiveButton("Ya",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteMateri();
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

    private void deleteMateri() {
        class DeleteMateri extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailMateri.this,
                        "Deleting Data...", "Harap menunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... params) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_DELETE_MAT, id);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                displayDetailData(message);
                Intent intent = new Intent(LihatDetailMateri.this, MainActivity.class);
                intent.putExtra("KeyName", "materi");
                startActivity(intent);
            }
        }
        DeleteMateri dm = new DeleteMateri();
        dm.execute();
    }

    private void updateMateri() {
        final String materi = edit_nama_mat.getText().toString().trim();

        class UpdateMateri extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailMateri.this,
                        "Updating Data...", "Harap menunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Konfigurasi.KEY_MAT_ID, id);
                hashMap.put(Konfigurasi.KEY_MAT_NAMA, materi);

                HttpHandler handler = new HttpHandler();
                String result = handler.sendPostRequest(Konfigurasi.URL_UPDATE_MAT, hashMap);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                displayDetailData(message);
            }
        }

        UpdateMateri um = new UpdateMateri();
        um.execute();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LihatDetailMateri.this);
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
                        Intent intent = new Intent(LihatDetailMateri.this, MainActivity.class);
                        intent.putExtra("KeyName", "materi");
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
                loading = ProgressDialog.show(LihatDetailMateri.this,
                        "Mengambil Data", "Harap menunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_DETAIL_MAT, id);
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

            String mat_id = object.getString(Konfigurasi.TAG_JSON_MAT_ID);
            String mat_nama = object.getString(Konfigurasi.TAG_JSON_MAT_NAMA);

            edit_id_mat.setText(mat_id);
            edit_nama_mat.setText(mat_nama);

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