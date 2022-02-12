package id.nicholasp.projectindividual;

public class Konfigurasi {
    //url dimana web API berada
    public static final String URL_GET_ALL_INS = "http://192.168.1.7/inixindo/instruktur/tr_datas_instruktur.php";
    public static final String URL_GET_DETAIL_INS = "http://192.168.1.7/inixindo/instruktur/tr_detail_instruktur.php?id_ins=";
    public static final String URL_ADD_INS = "http://192.168.1.7/inixindo/instruktur/tr_add_instruktur.php";
    public static final String URL_UPDATE_INS = "http://192.168.1.7/inixindo/instruktur/tr_update_instruktur.php";
    public static final String URL_DELETE_INS = "http://192.168.1.7/inixindo/instruktur/tr_delete_instruktur.php?id_ins=";

    public static final String URL_GET_ALL_MAT = "http://192.168.1.7/inixindo/materi/tr_datas_materi.php";
    public static final String URL_GET_DETAIL_MAT = "http://192.168.1.7/inixindo/materi/tr_detail_materi.php?id_mat=";
    public static final String URL_ADD_MAT = "http://192.168.1.7/inixindo/materi/tr_add_materi.php";
    public static final String URL_UPDATE_MAT = "http://192.168.1.7/inixindo/materi/tr_update_materi.php";
    public static final String URL_DELETE_MAT = "http://192.168.1.7/inixindo/materi/tr_delete_materi.php?id_mat=";

    public static final String URL_GET_ALL_PST = "http://192.168.1.7/inixindo/peserta/tr_datas_peserta.php";
    public static final String URL_GET_DETAIL_PST = "http://192.168.1.7/inixindo/peserta/tr_detail_peserta.php?id_pst=";
    public static final String URL_ADD_PST = "http://192.168.1.7/inixindo/peserta/tr_add_peserta.php";
    public static final String URL_UPDATE_PST = "http://192.168.1.7/inixindo/peserta/tr_update_peserta.php";
    public static final String URL_DELETE_PST = "http://192.168.1.7/inixindo/peserta/tr_delete_peserta.php?id_pst=";

    public static final String URL_GET_ALL_KLS = "http://192.168.1.7/inixindo/kelas/tr_datas_kelas.php";
    public static final String URL_GET_DETAIL_KLS = "http://192.168.1.7/inixindo/kelas/tr_detail_kelas.php?id_kls=";
    public static final String URL_ADD_KLS = "http://192.168.1.7/inixindo/kelas/tr_add_kelas.php";
    public static final String URL_UPDATE_KLS = "http://192.168.1.7/inixindo/kelas/tr_update_kelas.php";
    public static final String URL_DELETE_KLS = "http://192.168.1.7/inixindo/kelas/tr_delete_kelas.php?id_kls=";

    public static final String URL_GET_ALL_DT_KLS = "http://192.168.1.7/inixindo/detail_kelas/tr_datas_detail_kelas.php";
    public static final String URL_GET_DETAIL_DT_KLS = "http://192.168.1.7/inixindo/detail_kelas/tr_detail_detail_kelas.php?id_kls=";
    public static final String URL_GET_DETAIL_DETAIL_DT_KLS = "http://192.168.1.7/inixindo/detail_kelas/tr_detail_detail_detail_kelas.php?id_dt_kls=";
    public static final String URL_ADD_DT_KLS = "http://192.168.1.7/inixindo/detail_kelas/tr_add_detail_kelas.php";
    public static final String URL_UPDATE_DT_KLS = "http://192.168.1.7/inixindo/detail_kelas/tr_update_detail_kelas.php";
    public static final String URL_DELETE_DT_KLS = "http://192.168.1.7/inixindo/detail_kelas/tr_delete_detail_kelas.php?id_kls=";
    public static final String URL_DELETE_DT_DT_KLS = "http://192.168.1.7/inixindo/detail_kelas/tr_delete_detail_detail_kelas.php?id_dt_kls=";

    //key and value JSON yang muncul di browser
    public static final String KEY_INS_ID = "id_ins";
    public static final String KEY_INS_NAMA = "nama_ins";
    public static final String KEY_INS_EMAIL = "email_ins";
    public static final String KEY_INS_HP = "hp_ins";

    public static final String KEY_MAT_ID = "id_mat";
    public static final String KEY_MAT_NAMA = "nama_mat";

    public static final String KEY_PST_ID = "id_pst";
    public static final String KEY_PST_NAMA = "nama_pst";
    public static final String KEY_PST_EMAIL = "email_pst";
    public static final String KEY_PST_HP = "hp_pst";
    public static final String KEY_PST_INSTANSI = "instansi_pst";

    public static final String KEY_KLS_ID = "id_kls";
    public static final String KEY_KLS_TGL_MULAI = "tgl_mulai_kls";
    public static final String KEY_KLS_TGL_AKHIR = "tgl_akhir_kls";
    public static final String KEY_KLS_INS = "ins_kls";
    public static final String KEY_KLS_MAT = "mat_kls";

    public static final String KEY_DT_KLS_ID_KLS = "id_kls";
    public static final String KEY_DT_KLS_JUM = "jum_pst";

    // flag JSON
    public static final String TAG_JSON_ARRAY = "result";

    public static final String TAG_JSON_INS_ID = "id_ins";
    public static final String TAG_JSON_INS_NAMA = "nama_ins";
    public static final String TAG_JSON_INS_EMAIL = "email_ins";
    public static final String TAG_JSON_INS_HP = "hp_ins";

    public static final String TAG_JSON_MAT_ID = "id_mat";
    public static final String TAG_JSON_MAT_NAMA = "nama_mat";

    public static final String TAG_JSON_PST_ID = "id_pst";
    public static final String TAG_JSON_PST_NAMA = "nama_pst";
    public static final String TAG_JSON_PST_EMAIL = "email_pst";
    public static final String TAG_JSON_PST_HP = "hp_pst";
    public static final String TAG_JSON_PST_INSTANSI = "instansi_pst";

    public static final String TAG_JSON_KLS_ID = "id_kls";
    public static final String TAG_JSON_KLS_TGL_MULAI = "tgl_mulai_kls";
    public static final String TAG_JSON_KLS_TGL_AKHIR = "tgl_akhir_kls";
    public static final String TAG_JSON_KLS_INS = "nama_ins";
    public static final String TAG_JSON_KLS_MAT = "nama_mat";

    public static final String TAG_JSON_DT_KLS_ID_KLS = "id_kls";
    public static final String TAG_JSON_DT_KLS_JUM_PST = "jum_pst";
    public static final String TAG_JSON_DT_KLS_ID_DETAIL_KLS = "id_detail_kls";
    public static final String TAG_JSON_DT_KLS_NAMA_PST = "nama_pst";

    //variable ID nasabah
    public static final String INS_ID = "id_ins";
    public static final String MAT_ID = "id_mat";
    public static final String PST_ID = "id_pst";
    public static final String KLS_ID = "id_kls";
    public static final String DT_KLS_ID = "id_kls";
}
