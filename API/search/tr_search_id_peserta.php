<?php 

	$id_pst = $_POST['id_pst'];

	//Import File Koneksi Database
	require_once('../koneksi.php');
	
	//Membuat SQL Query
	$sql = "SELECT CONCAT(k.tgl_mulai_kls, space(1), m.nama_mat) kls_info
                FROM tb_detail_kelas dk
                JOIN tb_kelas k ON dk.id_kls=k.id_kls
                JOIN tb_materi m ON k.id_mat=m.id_mat
                WHERE dk.id_pst=$id_pst";
	
	//Mendapatkan Hasil
	$r = mysqli_query($con,$sql);
	
	//Membuat Array Kosong 
	$result = array();
	
	while($row = mysqli_fetch_array($r)){
		
		//Memasukkan Nama dan ID kedalam Array Kosong yang telah dibuat 
		array_push($result,array(
			"kls_info"=>$row['kls_info']
		));
	}
	
	//Menampilkan Array dalam Format JSON
	echo json_encode(array('result'=>$result));
	
	mysqli_close($con);
?>