package id.nicholasp.projectindividual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class LihatDetailDetailKelas extends AppCompatActivity
        implements AdapterView.OnItemClickListener {
    String id_kls;
    private ListView list_view;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_detail_detail_kelas);

        //penanganan list view
        list_view = findViewById(R.id.list_view_detail_detail_kelas);
        list_view.setOnItemClickListener(this);

        Intent receiveIntent = getIntent();
        id_kls = receiveIntent.getStringExtra(Konfigurasi.DT_KLS_ID);

        //method untuk ambil data JSON
        getJSON();
    }

//    //menampilkan options menu pada toolbar
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.navigation_item_tambah, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    //ketika salah satu options menu dipilih

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.nav_tambah:
//                startActivity(new Intent(this, TambahDataActivity.class));
//                break;
//            default:
//                Toast.makeText(this, "No Menu is selected", Toast.LENGTH_LONG).show();
//        }
//        return super.onOptionsItemSelected(item);
//    }

    private void getJSON() {
        //bantuan dari class AsynTask
        class GetJSON extends AsyncTask<Void, Void, String> { //inner class
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailDetailKelas.this,
                        "Mengambil Data", "Harap menunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_DETAIL_DT_KLS, id_kls);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                JSON_STRING = message;
                Log.d("DATA_JSON", JSON_STRING);

                //menampilkan data dalam bentuk list view
                displayAllData();
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void displayAllData() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            Log.d("DATA_JSON", JSON_STRING);

            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
                String get_id_kls = object.getString(Konfigurasi.TAG_JSON_DT_KLS_ID_KLS);
                String get_id_detail_kls = object.getString(Konfigurasi.TAG_JSON_DT_KLS_ID_DETAIL_KLS);
                String get_nama_pst = object.getString(Konfigurasi.TAG_JSON_DT_KLS_NAMA_PST);

                HashMap<String, String> detaildetailkelas = new HashMap<>();
                detaildetailkelas.put(Konfigurasi.TAG_JSON_DT_KLS_ID_KLS, get_id_kls);
                detaildetailkelas.put(Konfigurasi.TAG_JSON_DT_KLS_ID_DETAIL_KLS, get_id_detail_kls);
                detaildetailkelas.put(Konfigurasi.TAG_JSON_DT_KLS_NAMA_PST, get_nama_pst);

                //ubah format json menjadi array list
                list.add(detaildetailkelas);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // adapter untuk meletakan array list kedalam list view
        ListAdapter adapter = new SimpleAdapter(
                getApplicationContext(), list,
                R.layout.list_item_layout_kelas,
                new String[]{Konfigurasi.TAG_JSON_DT_KLS_ID_KLS, Konfigurasi.TAG_JSON_DT_KLS_ID_DETAIL_KLS, Konfigurasi.TAG_JSON_DT_KLS_NAMA_PST},
                new int[]{R.id.txt_id_kls, R.id.txt_mulai_kls, R.id.txt_akhir_kls}
        );
        list_view.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // ketika salah satu list dipilih
        // detail: ID, Name, Desg, Salary
        Intent myIntent = new Intent(LihatDetailDetailKelas.this,
                LihatDetailDetailDetailKelas.class);
        HashMap<String, String> map = (HashMap) parent.getItemAtPosition(position);
        String dtKlsId = map.get(Konfigurasi.TAG_JSON_DT_KLS_ID_DETAIL_KLS).toString();
        myIntent.putExtra("id_dt_kls", dtKlsId);
        startActivity(myIntent);
    }
}