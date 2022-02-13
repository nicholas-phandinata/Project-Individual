<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//Mendapatkan Nilai Dari Variable 
		$id_pst = $_POST['id_pst'];
		$nama_pst = $_POST['nama_pst'];
		$email_pst = $_POST['email_pst'];
		$hp_pst = $_POST['hp_pst'];
        $instansi_pst = $_POST['instansi_pst'];
		
		//import file koneksi database 
		require_once('../koneksi.php');
		
		//Membuat SQL Query
		$sql = "UPDATE tb_peserta SET nama_pst = '$nama_pst', email_pst = '$email_pst', hp_pst = '$hp_pst', instansi_pst = '$instansi_pst' WHERE id_pst = $id_pst;";
		
		//Meng-update Database 
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Update Data Instruktur';
		}else{
			echo 'Gagal Update Data Instruktur';
		}
		
		mysqli_close($con);
	}
?>