<?php 
	//Mendapatkan Nilai Dari Variable ID Pegawai yang ingin ditampilkan
	$id_pst = $_GET['id_pst'];
	
	//Importing database
	require_once('../koneksi.php');
	
	//Membuat SQL Query dengan pegawai yang ditentukan secara spesifik sesuai ID
	$sql = "SELECT * FROM tb_peserta WHERE id_pst=$id_pst";
	
	//Mendapatkan Hasil 
	$r = mysqli_query($con,$sql);
	
	//Memasukkan Hasil Kedalam Array
	$result = array();
	$row = mysqli_fetch_array($r);
	array_push($result,array(
			"id_pst"=>$row['id_pst'],
			"nama_pst"=>$row['nama_pst'],
			"email_pst"=>$row['email_pst'],
			"hp_pst"=>$row['hp_pst'],
            "instansi_pst"=>$row['instansi_pst']
		));

	//Menampilkan dalam format JSON
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
?>