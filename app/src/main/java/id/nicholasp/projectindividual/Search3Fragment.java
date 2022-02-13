package id.nicholasp.projectindividual;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Search3Fragment extends Fragment implements MainActivity.OnBackPressedListener, View.OnClickListener, AdapterView.OnItemClickListener{
    private Button button_search;
    private Button dateButton, dateButton2;
    private ListView listView;
    private String JSON_STRING;
    private ProgressDialog loading;
    String JSON_STRING1, slct_spin1, JSON_STRING2, slct_spin2, id_ins, id_mat, n1, n2, j1, j2;
    EditText edit_mulai_kelas, edit_akhir_kelas;
    Button btn_tambah_kelas;
    Spinner spn_id_pst;
    private int spinner_value, spinner_value2;
    private DatePickerDialog datePickerDialog;
    private DatePickerDialog datePickerDialog2;
    String url = "http://192.168.1.6/inixindo/search/tr_search_id_kelas.php";


    public Search3Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    private void getJSONpst() {
//        class GetJSONPst extends AsyncTask<Void, Void, String> { //inner class
//            ProgressDialog loading;
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                loading = ProgressDialog.show(getContext(),
//                        "Mengambil Data", "Harap menunggu...",
//                        false, false);
//            }
//
//            @Override
//            protected String doInBackground(Void... voids) {
//                HttpHandler handler = new HttpHandler();
//                String result = handler.sendGetResponse(Konfigurasi.URL_GET_ALL_PST);
//                return result;
//            }
//
//            @Override
//            protected void onPostExecute(String message) {
//                super.onPostExecute(message);
//                loading.dismiss();
//                Log.d("DATA_JSON: ", message);
//
//                JSON_STRING2= message;
//                JSONObject jsonObject = null;
//                ArrayList<String> listId = new ArrayList<>();
//                ArrayList<String> listNama = new ArrayList<>();
//
//                try {
//                    jsonObject = new JSONObject(JSON_STRING2);
//                    JSONArray jsonArray = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
//                    Log.d("Data_JSON_LIST: ", String.valueOf(jsonArray));
//
//
//                    for (int i=0;i<jsonArray.length(); i++){
//                        JSONObject object = jsonArray.getJSONObject(i);
//                        String id = object.getString("id_pst");
//                        String nama = object.getString("nama_pst");
//
//                        listId.add(id);
//                        listNama.add(nama);
//
//                        Log.d("DataArr: ", String.valueOf(id));
//                    }
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listNama);
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//                spn_id_pst.setAdapter(adapter);
//                spn_id_pst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                        spinner_value2 = Integer.parseInt(listId.get(i));
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> adapterView) {
//
//                    }
//                });
//            }
//        }
//        GetJSONPst getJSONPst = new GetJSONPst();
//        getJSONPst.execute();
//    }
//
public void openDatePicker()
{
    datePickerDialog.show();
}
public void openDatePicker2()
{
    datePickerDialog2.show();
}
    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }
    private String makeDateString(int day, int month, int year)
    {
        return year + "-" + month + "-" + day;
    }
    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(getContext(), style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private void initDatePicker2()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton2.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog2 = new DatePickerDialog(getContext(), style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search3, container, false);
//        getJSONpst();

//        spn_id_pst = view.findViewById(R.id.spinner2);
        initDatePicker();
        initDatePicker2();
        dateButton = view.findViewById(R.id.datePickerButton);
        dateButton2 = view.findViewById(R.id.datePickerButton2);
        dateButton.setText(getTodaysDate());
        dateButton2.setText(getTodaysDate());

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker();
            }
        });

        dateButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker2();
            }
        });

        listView = view.findViewById(R.id.listView);

        button_search = view.findViewById(R.id.button_search3);
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });


        return view;
    }
//
    private void getData() {
        class GetData extends AsyncTask<Void, Void, String> {
            String date = (String) dateButton.getText();
            String date2 = (String) dateButton2.getText();
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getContext(), "Ambil Data ", "Harap menunggu...", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("mulai", date);
                hashMap.put("akhir", date2);

                String result = handler.sendPostRequest(url,hashMap);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);

                loading.dismiss();
                JSON_STRING = message;
                Log.d("DATA_JSON: ", JSON_STRING);
                displaySearchResult(JSON_STRING);
            }
        }
        GetData getData = new GetData();
        getData.execute();
    }

    private void displaySearchResult(String json) {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        Log.d("json",json);

        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray jsonArray = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String kls_info = object.getString("kls_info");

                HashMap<String, String> res = new HashMap<>();
                res.put("kls_info", kls_info);


                list.add(res);
                Log.d("RES", String.valueOf(res));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // adapter untuk meletakkan array list kedalam list view
        ListAdapter adapter = new SimpleAdapter(
                getContext(), list, R.layout.list_item_layout_search,
                new String[]{"kls_info"},
                new int[]{R.id.txt_nama_pst}

        );
        listView.setAdapter(adapter);
//        listView.setVisibility(View.VISIBLE);

    }
    //
//
//    private void search_data(String val) {
//        Toast.makeText(getContext(), val, Toast.LENGTH_SHORT).show();
//    }
//
    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void doBack() {

    }
}