package id.nicholasp.projectindividual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class LihatDetailDetailDetailKelas extends AppCompatActivity {
    EditText edit_id_dt_kls, edit_dt_kls_inf, edit_nm_pst;
    String id;
    Button btn_update_dt_kls;
    Button btn_delete_dt_kls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_detail_detail_detail_kelas);

        edit_id_dt_kls = findViewById(R.id.edit_id_dt_kls);
        edit_dt_kls_inf = findViewById(R.id.edit_dt_kls_info);
        edit_nm_pst = findViewById(R.id.edit_nm_pst);

        //menerima intent dari class
        Intent receiveIntent = getIntent();
        id = receiveIntent.getStringExtra("id_dt_kls");
        edit_id_dt_kls.setText(id);

        //button
        btn_update_dt_kls = findViewById(R.id.btn_update_dt_kls);
        btn_delete_dt_kls = findViewById(R.id.btn_delete_dt_kls);

        btn_update_dt_kls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LihatDetailDetailDetailKelas.this, UpdateDetailKelas.class);
                intent.putExtra("id_dt_kls", id);
                startActivity(intent);
            }
        });
        btn_delete_dt_kls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LihatDetailDetailDetailKelas.this);
                alertDialogBuilder.setMessage("Yakin Delete?");

                alertDialogBuilder.setPositiveButton("Ya",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteDetailKelas();
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

    private void deleteDetailKelas() {
        class DeleteDetailKelas extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailDetailDetailKelas.this,
                        "Deleting Data...", "Harap menunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... params) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_DELETE_DT_DT_KLS, id);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                Intent intent = new Intent(LihatDetailDetailDetailKelas.this, MainActivity.class);
                intent.putExtra("KeyName", "detail kelas");
                startActivity(intent);
            }
        }
        DeleteDetailKelas ddk = new DeleteDetailKelas();
        ddk.execute();
    }

//    private void updateInstruktur() {
//        final String nama_ins = edit_nama_ins.getText().toString().trim();
//        final String email_ins = edit_email_ins.getText().toString().trim();
//        final String hp_ins = edit_hp_ins.getText().toString().trim();
//
//        class UpdateInstruktur extends AsyncTask<Void, Void, String> {
//            ProgressDialog loading;
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                loading = ProgressDialog.show(LihatDetailInstruktur.this,
//                        "Updating Data...", "Harap menunggu...",
//                        false, false);
//            }
//
//            @Override
//            protected String doInBackground(Void... params) {
//                HashMap<String, String> hashMap = new HashMap<>();
//                hashMap.put(Konfigurasi.KEY_INS_ID, id);
//                hashMap.put(Konfigurasi.KEY_INS_NAMA, nama_ins);
//                hashMap.put(Konfigurasi.KEY_INS_EMAIL, email_ins);
//                hashMap.put(Konfigurasi.KEY_INS_HP, hp_ins);
//
//                HttpHandler handler = new HttpHandler();
//                String result = handler.sendPostRequest(Konfigurasi.URL_UPDATE_INS, hashMap);
//                return result;
//            }
//
//            @Override
//            protected void onPostExecute(String message) {
//                super.onPostExecute(message);
//                loading.dismiss();
//                displayDetailData(message);
//            }
//        }
//
//        UpdateInstruktur ue = new UpdateInstruktur();
//        ue.execute();
//
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LihatDetailInstruktur.this);
//        alertDialogBuilder.setMessage("Update lagi?");
//
//        alertDialogBuilder.setPositiveButton("Ya",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                });
//
//        alertDialogBuilder.setNegativeButton("Tidak",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //startActivity(new Intent(LihatDetailInstruktur.this, InstrukturFragment.class));
//                    }
//                });
//
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.show();
//    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> { //inner class
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailDetailDetailKelas.this,
                        "Mengambil Data", "Harap menunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_DETAIL_DETAIL_DT_KLS, id);
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

            String id_detail = object.getString("id_dt_kls");
            String kelas_info = object.getString("kls_info");
            String nama = object.getString("nama_pst");

            edit_id_dt_kls.setText(id_detail);
            edit_dt_kls_inf.setText(kelas_info);
            edit_nm_pst.setText(nama);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }
}