package id.nicholasp.projectindividual;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class TambahDataKelas extends AppCompatActivity implements View.OnClickListener {
    String JSON_STRING1, slct_spin1, JSON_STRING2, slct_spin2, id_ins, id_mat, n1, n2, j1, j2;
    EditText edit_mulai_kelas, edit_akhir_kelas;
    Button btn_tambah_kelas;
    Spinner spn_id_ins, spn_id_mat;
    private int spinner_value, spinner_value2;
    String url = "http://192.168.1.8/inixindo/kelas/tr_add_kelas_mod.php?nama_ins=";
    String url2 = "http://192.168.1.8/inixindo/kelas/tr_add_kelas_mod2.php?nama_mat=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_kelas);

        spn_id_ins = findViewById(R.id.spinner1);
        spn_id_mat = findViewById(R.id.spinner2);

        edit_mulai_kelas = findViewById(R.id.edit_mulai_kelas);
        edit_akhir_kelas = findViewById(R.id.edit_akhir_kelas);
        btn_tambah_kelas = findViewById(R.id.btn_tambah_kelas);

        btn_tambah_kelas.setOnClickListener(this);

        getJSONins();
        getJSONmat();
    }

    private void getJSONins() {
        class GetJSONIns extends AsyncTask<Void, Void, String> { //inner class
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahDataKelas.this,
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

//                        arrayList.add(nama);
                        Log.d("DataArr: ", String.valueOf(id));
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TambahDataKelas.this, android.R.layout.simple_spinner_dropdown_item, listNama);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spn_id_ins.setAdapter(adapter);

                spn_id_ins.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        spinner_value = Integer.parseInt(listId.get(i));
                        Toast.makeText(TambahDataKelas.this, "True Value: "+spinner_value, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

//                slct_spin1 = spn_id_ins.getSelectedItem().toString();
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
                loading = ProgressDialog.show(TambahDataKelas.this,
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

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TambahDataKelas.this, android.R.layout.simple_spinner_dropdown_item, listNama);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spn_id_mat.setAdapter(adapter);
                spn_id_mat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        spinner_value2 = Integer.parseInt(listId.get(i));
                        Toast.makeText(TambahDataKelas.this, "True Value: "+spinner_value2, Toast.LENGTH_SHORT).show();
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
        tambahKelas();
    }

    private void tambahKelas() {
        Toast.makeText(this, spn_id_ins.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, spn_id_mat.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
        String mulai = edit_mulai_kelas.getText().toString().trim();
        String akhir = edit_akhir_kelas.getText().toString().trim();
//        namaInsToID(spn_id_ins.getSelectedItem().toString());
        String n1 = String.valueOf(spinner_value);
        String n2 = String.valueOf(spinner_value2);
//        namaMatToID(spn_id_mat.getSelectedItem().toString())
        Toast.makeText(this, "lol" + n1, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "lol2" + n2, Toast.LENGTH_SHORT).show();

        class TambahKelas extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahDataKelas.this,
                        "Menambah Data...", "Harap menunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("tgl_mulai_kls", mulai);
                hashMap.put("tgl_akhir_kls", akhir);
                hashMap.put("id_ins", n1);
                hashMap.put("id_mat", n2);

                HttpHandler handler = new HttpHandler();
                String result = handler.sendPostRequest(Konfigurasi.URL_ADD_KLS, hashMap);
                Log.d("Uwu", result);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
            }
        }
        TambahKelas tk = new TambahKelas();
        tk.execute();
    }

    private void namaInsToID(String namaIns) {
        Toast.makeText(this, namaIns, Toast.LENGTH_SHORT).show();
        String nama_ins = namaIns;

        class NamaInsToID extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                Toast.makeText(TambahDataKelas.this, "pre Ins", Toast.LENGTH_SHORT).show();
                super.onPreExecute();
                loading = ProgressDialog.show(TambahDataKelas.this,
                        "Menambah Data...", "Harap menunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... v) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(url, nama_ins);
                System.out.println("test: " + result);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                System.out.println("test Post: "+message);
                Toast.makeText(TambahDataKelas.this, "Post Ins" + message, Toast.LENGTH_SHORT).show();
                loading.dismiss();
                getInsVal(message);
            }
        }
        NamaInsToID nit = new NamaInsToID();
        nit.execute();
    }

    private void getInsVal(String me) {
        System.out.println("test me: " + me);
        j1 = me;


        JSONObject jsonObject = null;
        ArrayList<String> arrayList = new ArrayList<>();

        try {
            jsonObject = new JSONObject(j1);
            JSONArray jsonArray = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            Log.d("Data_JSON_LIST: ", String.valueOf(jsonArray));


            for (int i=0;i<jsonArray.length(); i++){


                JSONObject object = jsonArray.getJSONObject(i);
                String id = object.getString("id_ins");
                System.out.println("ke "+i+" - "+ id);
//                arrayList.add(id);
                id_ins = id;
            }
//            id_ins = arrayList.get(0);
            Log.d("Uwu: ", id_ins);

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(TambahDataKelas.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private String namaMatToID(String namaMat) {
        Toast.makeText(this, namaMat, Toast.LENGTH_SHORT).show();
        String nama_Mat = namaMat;

        class NamaMatToID extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                Toast.makeText(TambahDataKelas.this, "Pre Mat", Toast.LENGTH_SHORT).show();
                super.onPreExecute();
                loading = ProgressDialog.show(TambahDataKelas.this,
                        "Menambah Data...", "Harap menunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... v) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(url2, nama_Mat);
//                Toast.makeText(TambahDataKelas.this, result, Toast.LENGTH_SHORT).show();
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                Toast.makeText(TambahDataKelas.this, "Post Mat" + message, Toast.LENGTH_SHORT).show();

                j2 = message;

                JSONObject jsonObject = null;
                ArrayList<String> arrayList = new ArrayList<>();

                try {
                    jsonObject = new JSONObject(j2);
                    JSONArray jsonArray = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
                    Log.d("Data_JSON_LIST: ", String.valueOf(jsonArray));


                    for (int i=0;i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id = object.getString("id_mat");

                        arrayList.add(id);
                    }
                    id_mat = arrayList.get(0);
                    Log.d("Uwu: ", id_mat);

                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(TambahDataKelas.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }
        NamaMatToID mit = new NamaMatToID();
        mit.execute();
        return id_mat;
    }
}