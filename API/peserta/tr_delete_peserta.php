<?php 
 //Mendapatkan Nilai ID
 $id_pst = $_GET['id_pst'];
 
 //Import File Koneksi Database
 require_once('../koneksi.php');
 
 //Membuat SQL Query
 $sql = "DELETE FROM tb_peserta WHERE id_pst=$id_pst;";

 
 //Menghapus Nilai pada Database 
 if(mysqli_query($con,$sql)){
 echo 'Berhasil Menghapus Peserta';
 }else{
 echo 'Gagal Menghapus Peserta';
 }
 
 mysqli_close($con);
 ?>