<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//Mendapatkan Nilai Dari Variable 
		$id_ins = $_POST['id_ins'];
		$nama_ins = $_POST['nama_ins'];
		$email_ins = $_POST['email_ins'];
		$hp_ins = $_POST['hp_ins'];
		
		//import file koneksi database 
		require_once('../koneksi.php');
		
		//Membuat SQL Query
		$sql = "UPDATE tb_instruktur SET nama_ins = '$nama_ins', email_ins = '$email_ins', hp_ins = '$hp_ins' WHERE id_ins = $id_ins;";
		
		//Meng-update Database 
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Update Data Instruktur';
		}else{
			echo 'Gagal Update Data Instruktur';
		}
		
		mysqli_close($con);
	}
?>