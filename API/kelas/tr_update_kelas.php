<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//Mendapatkan Nilai Dari Variable 
		$id_kls = $_POST['id_kls'];
		$tgl_mulai_kls = $_POST['tgl_mulai_kls'];
		$tgl_akhir_kls = $_POST['tgl_akhir_kls'];
        $id_ins = $_POST['id_ins'];
        $id_mat = $_POST['id_mat'];
		
		//import file koneksi database 
		require_once('../koneksi.php');
		
		//Membuat SQL Query
		$sql = "UPDATE tb_kelas SET tgl_mulai_kls = '$tgl_mulai_kls', tgl_akhir_kls = '$tgl_akhir_kls', id_ins = '$id_ins', id_mat = '$id_mat' WHERE id_kls = $id_kls;";
		
		//Meng-update Database 
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Update Data Kelas';
		}else{
			echo 'Gagal Update Data Kelas';
		}
		
		mysqli_close($con);
	}
?>