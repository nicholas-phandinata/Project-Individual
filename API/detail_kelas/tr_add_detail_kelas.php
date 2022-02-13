<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		//Mendapatkan Nilai Variable
		$id_kls = $_POST['id_kls'];
		$id_pst = $_POST['id_pst'];
		
		//Pembuatan Syntax SQL
		$sql = "INSERT INTO tb_detail_kelas (id_kls,id_pst) VALUES ('$id_kls','$id_pst')";
		
		//Import File Koneksi database
		require_once('../koneksi.php');
		
		//Eksekusi Query database
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Menambahkan Detail Kelas';
		}else{
			echo 'Gagal Menambahkan Detail Kelas';
		}
		
		mysqli_close($con);
	}
?>