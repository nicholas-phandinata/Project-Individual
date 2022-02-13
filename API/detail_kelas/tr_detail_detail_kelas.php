<?php 
	//Mendapatkan Nilai Dari Variable ID Pegawai yang ingin ditampilkan
	$id_kls = $_GET['id_kls'];
	
	//Importing database
	require_once('../koneksi.php');
	
	//Membuat SQL Query dengan pegawai yang ditentukan secara spesifik sesuai ID
	$sql = "SELECT dk.id_kls id_kls, dk.id_detail_kls id_detail_kls, p.nama_pst nama_pst FROM tb_detail_kelas dk JOIN tb_peserta p ON dk.id_pst=p.id_pst WHERE dk.id_kls = $id_kls";
	
	//Mendapatkan Hasil 
	$r = mysqli_query($con,$sql);
	
	//Memasukkan Hasil Kedalam Array
	$result = array();

	while($row = mysqli_fetch_array($r)){
		
		//Memasukkan Nama dan ID kedalam Array Kosong yang telah dibuat 
		array_push($result,array(
			"id_kls"=>$row['id_kls'],
			"id_detail_kls"=>$row['id_detail_kls'],
                        "nama_pst"=>$row['nama_pst']
		));
	}

	//Menampilkan dalam format JSON
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
?>