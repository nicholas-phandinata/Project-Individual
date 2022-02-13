package id.nicholasp.projectindividual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
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

public class TambahDataDetailKelas extends AppCompatActivity implements View.OnClickListener {
    String JSON_STRING1, JSON_STRING2;
    Button btn_tambah_detail_kelas;
    Spinner spn1, spn2;
    private int spinner_value, spinner_value2;
    private Toolbar toolbar;
    String url = "http://192.168.1.103/inixindo/detail_kelas/tr_dropdown_ins_kelas.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_detail_kelas);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        spn1 = findViewById(R.id.spinnerdk1);
        spn2 = findViewById(R.id.spinnerdk2);
        btn_tambah_detail_kelas = findViewById(R.id.btn_tambah_detail_kelas);

        btn_tambah_detail_kelas.setOnClickListener(this);

        getJSONspn1();
        getJSONspn2();
    }

    private void getJSONspn1() {
        class GetJSONSpn1 extends AsyncTask<Void, Void, String> { //inner class
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahDataDetailKelas.this,
                        "Mengambil Data", "Harap menunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(url);
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
                        String id = object.getString("id_kls");
                        String nama = object.getString("kls_info");

                        listId.add(id);
                        listNama.add(nama);
                        Log.d("DataArr: ", String.valueOf(id));
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TambahDataDetailKelas.this, android.R.layout.simple_spinner_dropdown_item, listNama);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spn1.setAdapter(adapter);

                spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        GetJSONSpn1 getJSONSpn1 = new GetJSONSpn1();
        getJSONSpn1.execute();
    }

    private void getJSONspn2() {
        class GetJSONSpn2 extends AsyncTask<Void, Void, String> { //inner class
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahDataDetailKelas.this,
                        "Mengambil Data", "Harap menunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_ALL_PST);
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
                        String id = object.getString("id_pst");
                        String nama = object.getString("nama_pst");

                        listId.add(id);
                        listNama.add(nama);

                        Log.d("DataArr: ", String.valueOf(id));
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TambahDataDetailKelas.this, android.R.layout.simple_spinner_dropdown_item, listNama);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spn2.setAdapter(adapter);
                spn2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        GetJSONSpn2 getJSONSpn2 = new GetJSONSpn2();
        getJSONSpn2.execute();
    }

    @Override
    public void onClick(View v) {
        tambahDetailKelas();
    }

    private void tambahDetailKelas() {
        String n1 = String.valueOf(spinner_value);
        String n2 = String.valueOf(spinner_value2);

        class TambahDetailKelas extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahDataDetailKelas.this,
                        "Menambah Data...", "Harap menunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("id_kls", n1);
                hashMap.put("id_pst", n2);

                HttpHandler handler = new HttpHandler();
                String result = handler.sendPostRequest(Konfigurasi.URL_ADD_DT_KLS, hashMap);
                Log.d("Uwu", result);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                Intent intent = new Intent(TambahDataDetailKelas.this, MainActivity.class);
                intent.putExtra("KeyName", "detail kelas");
                startActivity(intent);
            }
        }
        TambahDetailKelas tdk = new TambahDetailKelas();
        tdk.execute();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}