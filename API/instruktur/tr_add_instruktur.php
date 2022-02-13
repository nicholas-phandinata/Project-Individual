<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		//Mendapatkan Nilai Variable
		$nama_ins = $_POST['nama_ins'];
		$email_ins = $_POST['email_ins'];
		$hp_ins = $_POST['hp_ins'];
		
		//Pembuatan Syntax SQL
		$sql = "INSERT INTO tb_instruktur (nama_ins,email_ins,hp_ins) VALUES ('$nama_ins','$email_ins','$hp_ins')";
		
		//Import File Koneksi database
		require_once('../koneksi.php');
		
		//Eksekusi Query database
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Menambahkan Instruktur';
		}else{
			echo 'Gagal Menambahkan Instruktur';
		}
		
		mysqli_close($con);
	}
?>