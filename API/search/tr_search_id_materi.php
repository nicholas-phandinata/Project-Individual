<?php 

	$id_mat = $_GET['id_mat'];

	//Import File Koneksi Database
	require_once('../koneksi.php');
	
	//Membuat SQL Query
	$sql = "SELECT DISTINCT(p.nama_pst)
                FROM tb_detail_kelas dk
                JOIN tb_kelas k ON dk.id_kls=k.id_kls
                JOIN tb_peserta p ON dk.id_pst=p.id_pst
                WHERE k.id_mat=$id_mat";
	
	//Mendapatkan Hasil
	$r = mysqli_query($con,$sql);
	
	//Membuat Array Kosong 
	$result = array();
	
	while($row = mysqli_fetch_array($r)){
		
		//Memasukkan Nama dan ID kedalam Array Kosong yang telah dibuat 
		array_push($result,array(
			"nama_pst"=>$row['nama_pst']
		));
	}
	
	//Menampilkan Array dalam Format JSON
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
?>