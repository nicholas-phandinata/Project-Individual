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

import id.nicholasp.projectindividual.databinding.FragmentPesertaBinding;

public class PesertaFragment extends Fragment implements MainActivity.OnBackPressedListener, View.OnClickListener {
    private FragmentPesertaBinding pesertaBinding;
    private View view;
    private String JSON_STRING;
    private ProgressDialog loading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        pesertaBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_peserta, container, false);
        ((MainActivity) getActivity()).setOnBackPressedListener(this);
        view = pesertaBinding.getRoot();
        initView();
        return view;
    }

    private void initView() {
        // custom action bar
        ActionBar customActionBar = ((MainActivity) getActivity()).getSupportActionBar();
        customActionBar.setTitle("Data Peserta");

        // penanganan List View
        pesertaBinding.listViewPeserta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), LihatDetailPeserta.class);
                HashMap<String, String> map = (HashMap) parent.getItemAtPosition(position);
                String pstId = map.get(Konfigurasi.TAG_JSON_PST_ID).toString();
                intent.putExtra(Konfigurasi.PST_ID, pstId);
                startActivity(intent);
            }
        });

        // penanganan FAB
        pesertaBinding.btnTambahPeserta.setOnClickListener(this);

        // ambil data dari JSON
        getJsonData();
    }

    private void getJsonData() {
        class GetJsonData extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(view.getContext(), "Ambil Data Peserta", "Harap menunggu...", false, false);
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
                JSON_STRING = message;
                Log.d("DATA_JSON: ", JSON_STRING);
                // Toast.makeText(view.getContext(), JSON_STRING, Toast.LENGTH_LONG).show();

                // menampilkan data json kedalam list view
                displayAllDataPeserta();
            }
        }
        GetJsonData getJsonData = new GetJsonData();
        getJsonData.execute();
    }

    private void displayAllDataPeserta() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray jsonArray = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String pst_id = object.getString(Konfigurasi.TAG_JSON_PST_ID);
                String nama_pst = object.getString(Konfigurasi.TAG_JSON_PST_NAMA);

                HashMap<String, String> peserta = new HashMap<>();
                peserta.put(Konfigurasi.TAG_JSON_PST_ID, pst_id);
                peserta.put(Konfigurasi.TAG_JSON_PST_NAMA, nama_pst);

                list.add(peserta);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // adapter untuk meletakkan array list kedalam list view
        ListAdapter adapter = new SimpleAdapter(
                view.getContext(), list, R.layout.list_item_layout,
                new String[]{Konfigurasi.TAG_JSON_PST_ID, Konfigurasi.TAG_JSON_PST_NAMA},
                new int[]{R.id.txt_id, R.id.txt_name}
        );
        pesertaBinding.listViewPeserta.setAdapter(adapter);
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
        startActivity(new Intent(view.getContext(), TambahDataPeserta.class));
    }
}