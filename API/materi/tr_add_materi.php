<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		//Mendapatkan Nilai Variable
		$nama_mat = $_POST['nama_mat'];
		
		//Pembuatan Syntax SQL
		$sql = "INSERT INTO tb_materi (nama_mat) VALUES ('$nama_mat')";
		
		//Import File Koneksi database
		require_once('../koneksi.php');
		
		//Eksekusi Query database
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Menambahkan Materi';
		}else{
			echo 'Gagal Menambahkan Materi';
		}
		
		mysqli_close($con);
	}
?>