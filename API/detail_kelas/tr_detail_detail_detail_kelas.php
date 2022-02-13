<?php 
	//Mendapatkan Nilai Dari Variable ID Pegawai yang ingin ditampilkan
	$id_dt_kls = $_GET['id_dt_kls'];
	
	//Importing database
	require_once('../koneksi.php');
	
	//Membuat SQL Query dengan pegawai yang ditentukan secara spesifik sesuai ID
	$sql = "SELECT dk.id_detail_kls id_dt_kls, CONCAT(k.tgl_mulai_kls, SPACE(1), m.nama_mat) kls_info, p.nama_pst nama_pst, dk.id_kls id_kls, dk.id_pst id_pst FROM tb_detail_kelas dk JOIN tb_peserta p ON dk.id_pst=p.id_pst JOIN tb_kelas k ON dk.id_kls=k.id_kls JOIN tb_materi m ON k.id_mat=m.id_mat WHERE id_detail_kls=$id_dt_kls";
	
	//Mendapatkan Hasil 
	$r = mysqli_query($con,$sql);
	
	//Memasukkan Hasil Kedalam Array
	$result = array();

	while($row = mysqli_fetch_array($r)){
		
		//Memasukkan Nama dan ID kedalam Array Kosong yang telah dibuat 
		array_push($result,array(
			"id_dt_kls"=>$row['id_dt_kls'],
                        "kls_info"=>$row['kls_info'],
			"nama_pst"=>$row['nama_pst']
		));
	}

	//Menampilkan dalam format JSON
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
?>