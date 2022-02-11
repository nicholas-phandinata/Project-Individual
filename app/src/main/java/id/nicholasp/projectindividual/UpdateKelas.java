package id.nicholasp.projectindividual;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdateKelas extends AppCompatActivity implements View.OnClickListener {
    EditText edit_id_kls, edit_mulai_kelas, edit_akhir_kelas;
    String id, JSON_STRING1, JSON_STRING2, n_ins, n_mat;
    Button button_update_conf_kelas;
    Spinner spn_id_ins, spn_id_mat;
    private int spinner_value, spinner_value2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_kelas);

        edit_id_kls = findViewById(R.id.edit_id_kls);
        edit_mulai_kelas = findViewById(R.id.edit_mulai_kelas);
        edit_akhir_kelas = findViewById(R.id.edit_akhir_kelas);
        spn_id_ins = findViewById(R.id.spinner1);
        spn_id_mat = findViewById(R.id.spinner2);
        button_update_conf_kelas = findViewById(R.id.btn_update_conf_kelas);

        Intent receiveIntent = getIntent();
        id = receiveIntent.getStringExtra(Konfigurasi.KLS_ID);
        edit_id_kls.setText(id);

        getJSON();
        getJSONins();
        getJSONmat();

        button_update_conf_kelas.setOnClickListener(this);

    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> { //inner class
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UpdateKelas.this,
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

            String mulai = object.getString(Konfigurasi.TAG_JSON_KLS_TGL_MULAI);
            String akhir = object.getString(Konfigurasi.TAG_JSON_KLS_TGL_AKHIR);
            n_ins = object.getString(Konfigurasi.TAG_JSON_KLS_INS);
            n_mat = object.getString(Konfigurasi.TAG_JSON_KLS_MAT);

            edit_mulai_kelas.setText(mulai);
            edit_akhir_kelas.setText(akhir);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getJSONins() {
        class GetJSONIns extends AsyncTask<Void, Void, String> { //inner class
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UpdateKelas.this,
                        "Mengambil Data", "Harap menunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_ALL_INS);
                System.out.println(result);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                Log.d("DATA_JSON: ", message);

                JSON_STRING1= message;
                JSONObject jsonObject = null;

                ArrayList<String> listId = new ArrayList<>();
                ArrayList<String> listNama = new ArrayList<>();

                try {
                    jsonObject = new JSONObject(JSON_STRING1);

                    JSONArray jsonArray = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
                    Log.d("Data_JSON_LIST: ", String.valueOf(jsonArray));


                    for (int i=0;i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id = object.getString("id_ins");
                        String nama = object.getString("nama_ins");

                        listId.add(id);
                        listNama.add(nama);
                        Log.d("DataArr: ", String.valueOf(id));
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateKelas.this, android.R.layout.simple_spinner_dropdown_item, listNama);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spn_id_ins.setAdapter(adapter);
                spn_id_ins.setSelection(listNama.indexOf(n_ins));
                spinner_value = Integer.parseInt(listId.get(listNama.indexOf(n_ins)));

                spn_id_ins.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        spinner_value = Integer.parseInt(listId.get(i));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
            }
        }
        GetJSONIns getJSONIns = new GetJSONIns();
        getJSONIns.execute();
    }

    private void getJSONmat() {
        class GetJSONMat extends AsyncTask<Void, Void, String> { //inner class
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UpdateKelas.this,
                        "Mengambil Data", "Harap menunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_ALL_MAT);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                Log.d("DATA_JSON: ", message);

                JSON_STRING2= message;
                JSONObject jsonObject = null;
                ArrayList<String> listId = new ArrayList<>();
                ArrayList<String> listNama = new ArrayList<>();

                try {
                    jsonObject = new JSONObject(JSON_STRING2);
                    JSONArray jsonArray = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
                    Log.d("Data_JSON_LIST: ", String.valueOf(jsonArray));


                    for (int i=0;i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id = object.getString("id_mat");
                        String nama = object.getString("nama_mat");

                        listId.add(id);
                        listNama.add(nama);

                        Log.d("DataArr: ", String.valueOf(id));
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateKelas.this, android.R.layout.simple_spinner_dropdown_item, listNama);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spn_id_mat.setAdapter(adapter);
                spn_id_mat.setSelection(listNama.indexOf(n_mat));
                spinner_value2 = Integer.parseInt(listId.get(listNama.indexOf(n_mat)));

                spn_id_mat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        spinner_value2 = Integer.parseInt(listId.get(i));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        }
        GetJSONMat getJSONMat = new GetJSONMat();
        getJSONMat.execute();
    }

    @Override
    public void onClick(View v) {
        updateconfKelas();
    }

    private void updateconfKelas() {
        String mulai = edit_mulai_kelas.getText().toString().trim();
        String akhir = edit_akhir_kelas.getText().toString().trim();
        int ins = spinner_value;
        int mat = spinner_value2;

        class UpdateConfKelas extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UpdateKelas.this,
                        "Updating Data...", "Harap menunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Konfigurasi.KEY_KLS_ID, id);
                hashMap.put(Konfigurasi.KEY_KLS_TGL_MULAI, mulai);
                hashMap.put(Konfigurasi.KEY_KLS_TGL_AKHIR, akhir);
                hashMap.put(Konfigurasi.KEY_INS_ID, String.valueOf(ins));
                hashMap.put(Konfigurasi.KEY_MAT_ID, String.valueOf(mat));

                HttpHandler handler = new HttpHandler();
                String result = handler.sendPostRequest(Konfigurasi.URL_UPDATE_KLS, hashMap);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                displayDetailData(message);
                Intent intent = new Intent(UpdateKelas.this, MainActivity.class);
                intent.putExtra("KeyName", "kelas");
                startActivity(intent);
            }
        }

        UpdateConfKelas uck = new UpdateConfKelas();
        uck.execute();
    }
}