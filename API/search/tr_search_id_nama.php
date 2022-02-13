<?php 

	$id_ins = $_GET['id_ins'];

	//Import File Koneksi Database
	require_once('../koneksi.php');
	
	//Membuat SQL Query
	$sql = "SELECT * FROM tb_instruktur WHERE id_ins=$id_ins";
	
	//Mendapatkan Hasil
	$r = mysqli_query($con,$sql);
	
	//Membuat Array Kosong 
	$result = array();
	
	while($row = mysqli_fetch_array($r)){
		
		//Memasukkan Nama dan ID kedalam Array Kosong yang telah dibuat 
		array_push($result,array(
			"id_ins"=>$row['id_ins'],
			"nama_ins"=>$row['nama_ins'],
            "email_ins"=>$row['email_ins'],
            "hp_ins"=>$row['hp_ins']
		));
	}
	
	//Menampilkan Array dalam Format JSON
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
?>