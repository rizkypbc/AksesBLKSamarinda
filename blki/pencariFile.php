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

$folder = "upload_filePencari/";


$urlcv = $_FILES['uploaded_file']['name'];

// $username = $_GET'username'];
// $username = $_POST['username'];
// $password = $_POST['password'];
// $noktp = $_POST['noktp'];
// $nama_pencari = $_POST['nama_pencari'];
// $jk_pencari = $_POST['jk_pencari'];
// $ttl_pencari = $_POST['ttl_pencari'];
// $provinsi = $_POST['provinsi'];
// $kab = $_POST['kab'];
// $asal = $_POST['asal'];
// $pendusernameikan = $_POST['pendusernameikan'];
// $ketrampilan = $_POST['ketrampilan'];
// $pengalaman = $_POST['pengalaman'];
// $alumni = $_POST['alumni'];
// $tanggal_daftar = date("Y-m-d");

$file_path = $folder . basename($_FILES['uploaded_file']['name']);
  
 

if (move_uploaded_file($_FILES['uploaded_file']['tmp_name'], $file_path)){
	
$sql1 = "SELECT max(id) as idd from pencari";
	 $result = mysqli_query($con, $sql1);
	 $hasil = mysqli_fetch_array($result);

	$sql = "UPDATE pencari SET urlcv = '$urlcv'  WHERE id= '$hasil[idd]'";
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
