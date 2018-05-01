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

$folder = "upload/";
$nama_file = $_FILES['uploaded_file']['name'];

$tanggal = date("Y-m-d");

$judul = $_POST['judul'];
$deskripsi = $_POST['deskripsi'];
$keterangan = $_POST['keterangan'];

$file_path = $folder . basename($_FILES['uploaded_file']['name']);

if (move_uploaded_file($_FILES['uploaded_file']['tmp_name'], $file_path)) {

	$sql = "INSERT INTO berita (tanggal, judul, deskripsi, nama_file, keterangan) VALUES ('$tanggal', '$judul', '$deskripsi', '$nama_file', 'No')";
	$stmt = mysqli_prepare($con,$sql);
		
		mysqli_stmt_bind_param($stmt,"s",$image);
		mysqli_stmt_execute($stmt);
		
		$check = mysqli_stmt_affected_rows($stmt);
		
		if($check == 1){
			echo "Image Uploaded Successfully";
		}else{
			echo "Error Uploading Image";
		}
		mysqli_close($con);
    // mysql_query("INSERT INTO file(judul, nama_file) VALUES('$judul', '$nama_file')") or die(mysql_error());
//     echo "success";
// } else {
//     echo "gambar tidak tersimpan didatabase";
}
?>