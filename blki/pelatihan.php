<?php

// $server = "localhost";
// $username = "root";
// $password = "";
// $database = "tutorial_upload";

// mysql_connect($server, $username, $password) or die(mysql_error());
// mysql_select_db($database) or die(mysql_error());

 define('HOST','localhost');
 define('USER','root');
 define('PASS','');
 define('DB','sampelblki');
 
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

$folder = "upload_pelatihan/";
$urlphoto = $_FILES['uploaded_file']['name'];
$nama = $_POST['nama'];
$jk = $_POST['jk'];
$ttl = $_POST['ttl'];
$alamat = $_POST['alamat'];
$provinsi = $_POST['provinsi'];
$kab_kota = $_POST['kab_kota'];
$notelp = $_POST['notelp'];
$email = $_POST['email'];
$agama = $_POST['agama'];
$pendidikan = $_POST['pendidikan'];
$jurusan = $_POST['jurusan'];
$asal_sekolah = $_POST['asal_sekolah'];
$kejuruan = $_POST['kejuruan'];
$sub_kejuruan = $_POST['sub_kejuruan'];
$program = $_POST['program'];

$tanggal_daftar = date("Y-m-d H:i:s");
$status = $_POST['status'];

$file_path = $folder . basename($_FILES['uploaded_file']['name']);

if (move_uploaded_file($_FILES['uploaded_file']['tmp_name'], $file_path)) {

	$sql = "INSERT INTO pelatihan (nama, jk, ttl, alamat, provinsi, kab_kota, notelp, email, agama, pendidikan, jurusan, asal_sekolah, kejuruan, sub_kejuruan, program, urlphoto, tanggal_daftar, status) VALUES ('$nama', '$jk', '$ttl', '$alamat', '$provinsi', '$kab_kota', '$notelp', '$email', '$agama', '$pendidikan', '$jurusan', '$asal_sekolah', '$kejuruan', '$sub_kejuruan', '$program', '$urlphoto', '$tanggal_daftar', 'Daftar Baru')";
	$stmt = mysqli_prepare($con,$sql);
		
		mysqli_stmt_bind_param($stmt,"s",$image);
		mysqli_stmt_execute($stmt);
		
		$check = mysqli_stmt_affected_rows($stmt);
		
		if($check == 1){
			echo "Data Tersimpan";
		}else{
			echo "Error, Data tidak tersimpan";
		}
		mysqli_close($con);
    // mysql_query("INSERT INTO file(judul, nama_file) VALUES('$judul', '$nama_file')") or die(mysql_error());
//     echo "success";
// } else {
//     echo "gambar tidak tersimpan didatabase";
}
?>