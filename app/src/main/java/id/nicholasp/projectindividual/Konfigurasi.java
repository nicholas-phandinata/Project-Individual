package id.nicholasp.projectindividual;

public class Konfigurasi {
    //url dimana web API berada
    public static final String URL_GET_ALL_INS = "http://192.168.1.8/inixindo/instruktur/tr_datas_instruktur.php";
    public static final String URL_GET_DETAIL_INS = "http://192.168.1.8/inixindo/instruktur/tr_detail_instruktur.php?id_ins=";
    public static final String URL_ADD_INS = "http://192.168.1.8/inixindo/instruktur/tr_add_instruktur.php";
    public static final String URL_UPDATE_INS = "http://192.168.1.8/inixindo/instruktur/tr_update_instruktur.php";
    public static final String URL_DELETE_INS = "http://192.168.1.8/inixindo/instruktur/tr_delete_instruktur.php?id=";

    //key and value JSON yang muncul di browser
    public static final String KEY_INS_ID = "id_ins";
    public static final String KEY_INS_NAMA = "nama_ins";
    public static final String KEY_INS_EMAIL = "email_ins";
    public static final String KEY_INS_HP = "hp_ins";

    // flag JSON
    public static final String TAG_JSON_ARRAY = "result";
    public static final String TAG_JSON_INS_ID = "id_ins";
    public static final String TAG_JSON_INS_NAMA = "nama_ins";
    public static final String TAG_JSON_INS_EMAIL = "email_ins";
    public static final String TAG_JSON_INS_HP = "hp_ins";

    //variable ID nasabah
    public static final String INS_ID = "id_ins";
}
