<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		//Mendapatkan Nilai Variable
		$tgl_mulai_kls = $_POST['tgl_mulai_kls'];
		$tgl_akhir_kls = $_POST['tgl_akhir_kls'];
                $id_ins = (int)$_POST['id_ins'];
		$id_mat = (int)$_POST['id_mat'];
		
		//Pembuatan Syntax SQL
		$sql = "INSERT INTO tb_kelas (tgl_mulai_kls,tgl_akhir_kls,id_ins,id_mat) VALUES ('$tgl_mulai_kls','$tgl_akhir_kls', '$id_ins', '$id_mat')";
		
		//Import File Koneksi database
		require_once('../koneksi.php');
		
		//Eksekusi Query database
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Menambahkan Kelas';
		}else{
			echo 'Gagal Menambahkan Kelas';
		}
		
		mysqli_close($con);
	}
?>