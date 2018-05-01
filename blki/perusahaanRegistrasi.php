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

$folder = "upload_perusahaan/";

$logo = $_FILES['uploaded_file']['name'];

$username = $_POST['username'];
$password = $_POST['password'];
$nama_perusahaan = $_POST['nama_perusahaan'];
$jenis = $_POST['jenis'];
$sektor = $_POST['sektor'];
$bidang = $_POST['bidang'];
$alamat_perusahaan = $_POST['alamat_perusahaan'];
$provinsi = $_POST['provinsi'];
$kab = $_POST['kab'];
$telepon = $_POST['telepon'];
$fax = $_POST['fax'];
$email = $_POST['email'];
$website = $_POST['website'];
$profil_perusahaan = $_POST['profil_perusahaan'];
$pj = $_POST['pj'];
$jabatan = $_POST['jabatan'];
$hp = $_POST['hp'];
$ket_lain = $_POST['ket_lain'];

$tanggal_daftar = date("Y-m-d H:i:s");
$status = $_POST['status'];

$file_path = $folder . basename($_FILES['uploaded_file']['name']);

if (move_uploaded_file($_FILES['uploaded_file']['tmp_name'], $file_path)) {

	$sql = "INSERT INTO perusahaan (username, password, nama_perusahaan, jenis, sektor, bidang, alamat_perusahaan, provinsi, kab, telepon, fax, email, website, profil_perusahaan, logo, pj, jabatan, hp, ket_lain, tanggal_daftar, status) VALUES ('$username', '$password', '$nama_perusahaan', '$jenis', '$sektor', '$bidang', '$alamat_perusahaan', '$provinsi', '$kab', '$telepon', '$fax', '$email', '$website', '$profil_perusahaan', '$logo', '$pj', '$jabatan', '$hp', '$ket_lain', '$tanggal_daftar', 'Non Aktif')";
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