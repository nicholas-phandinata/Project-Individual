<?php 
 //Mendapatkan Nilai ID
 $id_dt_kls = $_GET['id_dt_kls'];
 
 //Import File Koneksi Database
 require_once('../koneksi.php');
 
 //Membuat SQL Query
 $sql = "DELETE FROM tb_detail_kelas WHERE id_detail_kls=$id_dt_kls;";

 
 //Menghapus Nilai pada Database 
 if(mysqli_query($con,$sql)){
 echo 'Berhasil Menghapus Detail Kelas';
 }else{
 echo 'Gagal Menghapus Detail Kelas';
 }
 
 mysqli_close($con);
 ?>