<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//Mendapatkan Nilai Dari Variable 
		$id_mat = $_POST['id_mat'];
		$nama_mat = $_POST['nama_mat'];
		
		//import file koneksi database 
		require_once('../koneksi.php');
		
		//Membuat SQL Query
		$sql = "UPDATE tb_materi SET nama_mat = '$nama_mat' WHERE id_mat = $id_mat;";
		
		//Meng-update Database 
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Update Data Materi';
		}else{
			echo 'Gagal Update Data Materi';
		}
		
		mysqli_close($con);
	}
?>