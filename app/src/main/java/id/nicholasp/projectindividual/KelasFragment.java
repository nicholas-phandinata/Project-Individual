package id.nicholasp.projectindividual;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import id.nicholasp.projectindividual.databinding.FragmentKelasBinding;

public class KelasFragment extends Fragment implements MainActivity.OnBackPressedListener, View.OnClickListener {
    private FragmentKelasBinding kelasBinding;
    private View view;
    private String JSON_STRING;
    private ProgressDialog loading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        kelasBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_kelas, container, false);
        ((MainActivity) getActivity()).setOnBackPressedListener(this);
        view = kelasBinding.getRoot();
        initView();
        return view;
    }

    private void initView() {
        // custom action bar
        ActionBar customActionBar = ((MainActivity) getActivity()).getSupportActionBar();
        customActionBar.setTitle("Data Kelas");

        // penanganan List View
        kelasBinding.listViewKelas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getActivity(), LihatDetailInstruktur.class);
//                HashMap<String, String> map = (HashMap) parent.getItemAtPosition(position);
//                String insId = map.get(Konfigurasi.TAG_JSON_INS_ID).toString();
//                intent.putExtra(Konfigurasi.INS_ID, insId);
//                startActivity(intent);
            }
        });

        // penanganan FAB
//        materiBinding.btnTambahInstruktur.setOnClickListener(this);

        // ambil data dari JSON
        getJsonData();
    }

    private void getJsonData() {
        class GetJsonData extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(view.getContext(), "Ambil Data Kelas", "Harap menunggu...", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_ALL_KLS);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                JSON_STRING = message;
                Log.d("DATA_JSON: ", JSON_STRING);
                // Toast.makeText(view.getContext(), JSON_STRING, Toast.LENGTH_LONG).show();

                // menampilkan data json kedalam list view
                displayAllDataKelas();
            }
        }
        GetJsonData getJsonData = new GetJsonData();
        getJsonData.execute();
    }

    private void displayAllDataKelas() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray jsonArray = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String kls_id = object.getString(Konfigurasi.TAG_JSON_KLS_ID);
                String tgl_mulai_kls = object.getString(Konfigurasi.TAG_JSON_KLS_TGL_MULAI);
                String tgl_akhir_kls = object.getString(Konfigurasi.TAG_JSON_KLS_TGL_AKHIR);

                HashMap<String, String> kelas = new HashMap<>();
                kelas.put(Konfigurasi.TAG_JSON_KLS_ID, kls_id);
                kelas.put(Konfigurasi.TAG_JSON_KLS_TGL_MULAI, tgl_mulai_kls);
                kelas.put(Konfigurasi.TAG_JSON_KLS_TGL_AKHIR, tgl_akhir_kls);

                list.add(kelas);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // adapter untuk meletakkan array list kedalam list view
        ListAdapter adapter = new SimpleAdapter(
                view.getContext(), list, R.layout.list_item_layout_kelas,
                new String[]{Konfigurasi.TAG_JSON_KLS_ID, Konfigurasi.TAG_JSON_KLS_TGL_MULAI, Konfigurasi.TAG_JSON_KLS_TGL_AKHIR},
                new int[]{R.id.txt_id_kls, R.id.txt_mulai_kls, R.id.txt_akhir_kls}
        );
        kelasBinding.listViewKelas.setAdapter(adapter);
    }

    @Override
    public void doBack() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.replace(R.id.frameLayout, new MateriFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        // penanganan FAB
//        startActivity(new Intent(view.getContext(), TambahDataInstruktur.class));
    }
}