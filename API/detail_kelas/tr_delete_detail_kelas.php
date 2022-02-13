<?php 
 //Mendapatkan Nilai ID
 $id_kls = $_GET['id_kls'];
 
 //Import File Koneksi Database
 require_once('../koneksi.php');
 
 //Membuat SQL Query
 $sql = "DELETE FROM tb_detail_kelas WHERE id_kls=$id_kls;";

 
 //Menghapus Nilai pada Database 
 if(mysqli_query($con,$sql)){
 echo 'Berhasil Menghapus Detail Kelas';
 }else{
 echo 'Gagal Menghapus Detail Kelas';
 }
 
 mysqli_close($con);
 ?>