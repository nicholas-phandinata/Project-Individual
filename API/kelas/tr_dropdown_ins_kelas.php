<?php 
	//Import File Koneksi Database
	require_once('../koneksi.php');
	
	//Membuat SQL Query
	$sql = "SELECT k.id_kls id_kls, CONCAT(k.tgl_mulai_kls, SPACE(1), m.nama_mat) kls_info FROM tb_kelas k JOIN tb_materi m ON k.id_mat=m.id_mat";
	
	//Mendapatkan Hasil
	$r = mysqli_query($con,$sql);
	
	//Membuat Array Kosong 
	$result = array();
	
	while($row = mysqli_fetch_array($r)){
		
		//Memasukkan Nama dan ID kedalam Array Kosong yang telah dibuat 
		array_push($result,array(
			"id_kls"=>$row['id_kls'],
			"kls_info"=>$row['kls_info']
		));
	}
	
	//Menampilkan Array dalam Format JSON
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
?>