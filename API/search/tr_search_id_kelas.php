<?php 

	$mulai = $_POST['mulai'];
        $akhir = $_POST['akhir'];

	//Import File Koneksi Database
	require_once('../koneksi.php');
	
	//Membuat SQL Query
	$sql = "SELECT DISTINCT(CONCAT(m.nama_mat, ' By ', i.nama_ins)) kls_info
                FROM tb_kelas k
                JOIN tb_instruktur i ON k.id_ins=i.id_ins
                JOIN tb_materi m on k.id_mat=m.id_mat
                WHERE tgl_mulai_kls BETWEEN '$mulai' and '$akhir'";
	
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