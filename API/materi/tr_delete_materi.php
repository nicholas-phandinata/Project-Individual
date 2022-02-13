<?php 
 //Mendapatkan Nilai ID
 $id_mat = $_GET['id_mat'];
 
 //Import File Koneksi Database
 require_once('../koneksi.php');
 
 //Membuat SQL Query
 $sql = "DELETE FROM tb_materi WHERE id_mat=$id_mat;";

 
 //Menghapus Nilai pada Database 
 if(mysqli_query($con,$sql)){
 echo 'Berhasil Menghapus Materi';
 }else{
 echo 'Gagal Menghapus Materi';
 }
 
 mysqli_close($con);
 ?>